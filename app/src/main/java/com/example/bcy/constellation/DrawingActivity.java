package com.example.bcy.constellation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DrawingActivity extends AppCompatActivity {

    Canvas canvas = new Canvas();
    Paint paint = new Paint();
    View star1, star2;
    float startX, startY, endX, endY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawing_map);

        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(1f);

       /* star1 = findViewById(R.id.star1);
        star2 = findViewById(R.id.star2);

        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startX = v.getX();
                startY = v.getY();
            }
        });

        star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endX = v.getX();
                endY = v.getY();
            }
        });*/

//        canvas.drawLine(startX, startY, endX, endY, paint);
    }
}
