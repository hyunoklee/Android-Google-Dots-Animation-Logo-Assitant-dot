package com.example.mylibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class GoogleAssitant extends View {

    private String TAG = "GoogleDotUserSpeaking";
    private Paint paint0;
    private Paint paint1;
    private Paint paint2;
    private Paint paint3;
    private int Width = 0;
    private boolean init = true;

    public GoogleAssitant(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint0 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint0.setColor(Color.parseColor("#5A9EF1"));
        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setColor(Color.parseColor("#E85952"));
        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setColor(Color.parseColor("#F0CB1A"));
        paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint3.setColor(Color.parseColor("#45CA75"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (init) {
            Width = getWidth();
            init = false;
            //loading();
        }

        canvas.drawCircle((float) (0.31*Width), (float) (0.354*Width), (float) (0.31*Width), paint0);
        canvas.drawCircle((float) (0.772*Width), (float) (0.534*Width), (float) (0.115*Width), paint1);
        canvas.drawCircle((float) (0.772*Width), (float) (0.834*Width), (float) (0.138*Width), paint2);
        canvas.drawCircle((float) (0.938*Width), (float) (0.36*Width), (float) (0.055*Width), paint3);
        Log.d("test","test3");
    }

}
