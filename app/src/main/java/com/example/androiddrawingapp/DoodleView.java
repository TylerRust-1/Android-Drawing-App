package com.example.androiddrawingapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class DoodleView extends View {

    public LayoutParams params;
    private Path path = new Path();
    private Paint brush = new Paint();


    public DoodleView(Context context) {
        super(context);

        brush.setAntiAlias(true);
        brush.setColor(Color.BLACK);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(8f);

        params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
}
