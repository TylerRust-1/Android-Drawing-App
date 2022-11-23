package com.example.androiddrawingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clearCanvas(View view) {
        DoodleView dv = findViewById(R.id.doodleView);
        dv.clearCanvas();
    }
}