package com.example.androiddrawingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.google.android.material.slider.Slider;

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

    public void openMenu(View view){
        DoodleView dv = findViewById(R.id.doodleView);
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
}