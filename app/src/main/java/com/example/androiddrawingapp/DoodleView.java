package com.example.androiddrawingapp;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class DoodleView extends View {

    List<Pair<Path, Integer>> path_color_list = new ArrayList<>();
    public LayoutParams params;
    private Path path = new Path();
    private Paint brush = new Paint();



    public DoodleView(Context context) {
        super(context);
        init(context);
    }

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DoodleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context){
        brush.setAntiAlias(true);
        brush.setColor(Color.BLACK);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(8f);
        params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float pointX = event.getX();
        float pointY = event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointX, pointY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointX, pointY);
                break;
            default:
                return false;
        }
        postInvalidate();
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int temp = brush.getColor();
        for (Pair<Path,Integer> path_clr : path_color_list ){
            brush.setColor(path_clr.second);
            canvas.drawPath( path_clr.first, brush);
        }
        brush.setColor(temp);
        canvas.drawPath(path, brush);
    }

    public void clearCanvas() {
        path.reset();
        postInvalidate();
    }

    public void setColor(Integer color) {
        path_color_list.add(Pair.create(path, brush.getColor()));
        path = new Path();
        brush.setColor(color);

        postInvalidate();
    }
}
