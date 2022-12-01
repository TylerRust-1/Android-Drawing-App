package com.example.androiddrawingapp;


import static java.lang.Math.round;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class DoodleView extends View {

    List<Pair<Path, Pair<Integer, Integer>>> path_color_list = new ArrayList<>();
    public LayoutParams params;
    private Path path = new Path();
    private Paint brush = new Paint();
    private Bitmap buffer;

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
        path_color_list.add(Pair.create(path, Pair.create(brush.getColor(), round(brush.getStrokeWidth()))));
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
        if (buffer != null) {
            canvas.drawBitmap(buffer, 0, 0, brush);
        }
        canvas.drawPath(path, brush);
        for (Pair<Path, Pair<Integer, Integer>> path_clr : path_color_list ){
            brush.setColor(path_clr.second.first);
            brush.setStrokeWidth(path_clr.second.second);
            canvas.drawPath( path_clr.first, brush);
        }
    }

    public void clearCanvas() {
        for (Pair<Path, Pair<Integer, Integer>> path_clr : path_color_list ){
            path_clr.first.reset();
        }
        buffer = null;
        postInvalidate();
    }

    public void setColor(Integer color) {
        path = new Path();
        brush.setColor(color);
        path_color_list.add(Pair.create(path, Pair.create(brush.getColor(), round(brush.getStrokeWidth()))));
        postInvalidate();
    }

    public void setWidth(Integer width) {
        path = new Path();
        brush.setStrokeWidth(width);
        path_color_list.add(Pair.create(path, Pair.create(brush.getColor(), round(brush.getStrokeWidth()))));
        postInvalidate();
    }

    public void load(Bitmap bitmap) {
        clearCanvas();
        buffer = bitmap;
    }
}
