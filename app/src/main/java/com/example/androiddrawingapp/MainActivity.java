package com.example.androiddrawingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.nvt.color.ColorPickerDialog;

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

    public void openMenu(View view) {
        DoodleView dv = findViewById(R.id.doodleView);
        ColorPickerDialog.OnColorPickerListener test = new ColorPickerDialog.OnColorPickerListener() {
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
                test);
        colorPicker.show();
    }

    /*
    public void openMenu(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);
        Slider slider = new Slider(dv.getContext());
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.slider:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("")));
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();
    }
    */
}