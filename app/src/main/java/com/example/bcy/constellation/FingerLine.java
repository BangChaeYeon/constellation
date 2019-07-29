package com.example.bcy.constellation;


import android.content.Context;
import android.graphics.*;
import android.graphics.Paint.Style;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.*;

import java.util.ArrayList;

class drawValue {

    private float startX2;
    private float startY2;
    private float endX2;
    private float endY2;

    public drawValue(float sx2, float sy2, float ex2, float ey2){
        startX2 = sx2;
        startY2 = sy2;
        endX2 = ex2;
        endY2 = ey2;
    }
    public float getStartX2(){
        return startX2;
    }
    public float getStartY2(){
        return startY2;
    }
    public float getEndX2(){
        return endX2;
    }
    public float getEndY2(){
        return endY2;
    }
}
class drawValueCir{

    private float x;
    private float y;

    public drawValueCir(int x1, int y1){
        x = x1;
        y = y1;
    }
    public float getX2(){
        return x;
    }
    public float getY2(){
        return y;
    }
}

public class FingerLine extends View {
    Canvas canvas;
    private final Paint mPaint;
    private float startX;
    private float startY;
    private float endX;
    private float endY;

    ArrayList<drawValue> dv = new ArrayList<drawValue>(); // ArrayList를 만들어봐여~ (drawline 값들을 위해)

    ArrayList<drawValueCir> dc = new ArrayList<drawValueCir>();

    public FingerLine(Context context) {
        this(context, null);
    }

    public FingerLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Style.FILL_AND_STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(drawValue sd : dv){ // sd는 sample drawValue // ArrayList에 있는 값 불러오기
            canvas.drawLine(sd.getStartX2(), sd.getStartY2(), sd.getEndX2(), sd.getEndY2(), mPaint);
        }
        dc.add(new drawValueCir( 90, 224));
        dc.add(new drawValueCir( 592, 346));
        dc.add(new drawValueCir( 74, 420));
        dc.add(new drawValueCir( 96, 468));
        dc.add(new drawValueCir( 192, 420));
        dc.add(new drawValueCir( 664, 338));
        dc.add(new drawValueCir( 692, 428));
        dc.add(new drawValueCir( 704, 476));

//        dc.add(new drawValueCir(45 ,112 ));
//        dc.add(new drawValueCir(296 ,173 ));
//        dc.add(new drawValueCir(37 ,210 ));
//        dc.add(new drawValueCir(48 ,234 ));
//        dc.add(new drawValueCir(96 ,210 ));
//        dc.add(new drawValueCir(332 ,169 ));
//        dc.add(new drawValueCir(346 ,214 ));
//        dc.add(new drawValueCir(352 ,238 ));

        for(drawValueCir sd : dc) {
            canvas.drawCircle(sd.getX2(), sd.getY2(), 15, mPaint );
        }
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // 눌렀을 때
                startX = event.getX(); // 시작 좌표의 X
                startY = event.getY(); // 시작 좌표의 Y
                break;

            case MotionEvent.ACTION_MOVE: // 움직일 때
                // 움직일때도 값 지정하면 화면 더러워짐...
                break;

            case MotionEvent.ACTION_UP: // 떼을 때
                endX = event.getX(); // 끝 좌표의 X
                endY = event.getY(); // 끝 좌표의 Y
                dv.add(new drawValue(startX, startY, endX, endY)); // 밑의 invalidate()를 하면 화면이 지워지므로 값 저장
                invalidate(); // 화면을 지우면서 onDraw() 호출
                startX = 0; startY = 0; endX = 0; endY = 0; // 값 초기화
                break;
        }
        return true;
    }
}