package com.example.androiddrawingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.nvt.color.ColorPickerDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=(SeekBar)findViewById(R.id.brushSize);
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

    Bitmap getBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(
                view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888
        );
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    public void save(View view) {
        DoodleView dv = findViewById(R.id.doodleView);
        Bitmap bitmap = getBitmapFromView(dv);
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();

        int b = (int)(Math.random()*(100000));
        System.out.println(path);
        File file = new File(path + "/image" + UUID.randomUUID() + ".png");
        FileOutputStream ostream;
        try {
            file.createNewFile();
            ostream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
            ostream.flush();
            ostream.close();

            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error: image not saved", Toast.LENGTH_SHORT).show();
        }
    }

    Bitmap bitmap;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        DoodleView dv = findViewById(R.id.doodleView);
        if (requestCode == 1) {
            Context context = dv.getContext();
            try {
                dv.clearCanvas();
                InputStream inputStream = context.getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(inputStream);
                dv.load(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void load(View view){
        DoodleView dv = findViewById(R.id.doodleView);
        getImage();

    }

    public void getImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }
}