package com.example.androiddrawingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import com.nvt.color.ColorPickerDialog;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=(SeekBar)findViewById(R.id.seekBar3);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                DoodleView dv = findViewById(R.id.doodleView);
                dv.setWidth((Integer) i);
                System.out.println("Hello");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void clearCanvas(View view) {
        DoodleView dv = findViewById(R.id.doodleView);
        dv.clearCanvas();
    }

    public void openMenu(View view) {
        DoodleView dv = findViewById(R.id.doodleView);
        ColorPickerDialog.OnColorPickerListener dialog = new ColorPickerDialog.OnColorPickerListener() {
            @Override
            public void onCancel(ColorPickerDialog dialog) {
                //none
            }

            @Override
            public void onOk(ColorPickerDialog dialog, int color) {
                dv.setColor(color);
            }
        };

        ColorPickerDialog colorPicker = new ColorPickerDialog(
                this,
                Color.BLACK,
                true,
                dialog);

        colorPicker.show();
    }
}