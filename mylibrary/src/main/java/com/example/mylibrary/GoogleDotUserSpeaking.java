/*
 * Copyright 2015 guohuanwen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.mylibrary;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class GoogleDotUserSpeaking extends View {
    //private Paint paint;
    private Paint paint0;
    private Paint paint1;
    private Paint paint2;
    private Paint paint3;
    private boolean init = false;
    private float width = 0;
    private float height = 0;
    private ValueAnimator valueAnimator;
    private float numb = 0;
    private boolean stop = false;
    private float[] initLine = new float[]{0.6f, 0.3f, 0.0f, 0.3f, 0.6f};
    float round = 0;
    float rtWidth = 0;
    float ratio = 0;

    public GoogleDotUserSpeaking(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint0 = new Paint(Paint.ANTI_ALIAS_FLAG);
        //paint0.setStrokeWidth(10);
        paint0.setColor(Color.parseColor("#5A9EF1"));

        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        //paint1.setStrokeWidth(10);
        paint1.setColor(Color.parseColor("#E85952"));

        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        //paint2.setStrokeWidth(10);
        paint2.setColor(Color.parseColor("#F0CB1A"));

        paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        //paint3.setStrokeWidth(10);
        paint3.setColor(Color.parseColor("#45CA75"));

        ratio = (float) 0.5;

    }

    public void initLine(float[] floats) {
        if (floats.length >= 5) {
            this.initLine = floats;
        }
    }

    public void setHeightRatio(float ratio_data) {
        ratio = ratio_data ;
    }

    @SuppressLint("NewApi")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!init) {
            width = getWidth();
            height = getHeight();
            init = true;
            round = width/5;
            rtWidth = width/10;
            start();
        }
        numb = (float) valueAnimator.getAnimatedValue();
       float top[] ={width/2 - fx(numb + 0)* width/2*ratio,width/2 - fx((float) (numb + 0.5))* width/2*ratio};
        float bottom[] ={width/2 + fx(numb + 0) * width/2*ratio,width/2 + fx((float) (numb + 0.5)) * width/2*ratio};

        if(Math.abs(top[0]-bottom[0]) < rtWidth){
            top[0] = width/2 - rtWidth/2 ;
            bottom[0] = width/2 + rtWidth/2;
        }
        if(Math.abs(top[1]-bottom[1]) < rtWidth){
            top[1] = width/2 - rtWidth/2 ;
            bottom[1] = width/2 + rtWidth/2;
        }

        canvas.drawRoundRect(new RectF(width *1 / 5, top[0], width *1 / 5 + rtWidth, bottom[0]),round,round,paint0);
        canvas.drawRoundRect(new RectF(width *2 / 5, top[1], width *2 / 5 + rtWidth, bottom[1]),round,round,paint1);
        canvas.drawRoundRect(new RectF(width *3 / 5, top[0], width *3 / 5 + rtWidth, bottom[0]),round,round,paint2);
        canvas.drawRoundRect(new RectF(width *4 / 5, top[1], width *4 / 5 + rtWidth, bottom[1]),round,round,paint3);

        if (valueAnimator.isRunning()) {
            invalidate();
        }
    }

    private float fx(float numb) {
        if (numb <= 0 && numb > -1) {
            numb = Math.abs(numb);
        }
        if (numb <= -1) {
            numb = (numb + 2);
        }
        if (numb > 1) {
            numb = (-numb + 2);
        }

        return numb;
    }

    public void start() {
        if (valueAnimator == null) {
            valueAnimator = getValueAnimator();
        } else {
            valueAnimator.start();
        }
        if (stop == false) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    start();
                    invalidate();
                }
            }, valueAnimator.getDuration());
        }
    }

    public void stop() {
        valueAnimator.cancel();
        this.stop = true;
    }

    private ValueAnimator getValueAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(-1f, 1f);
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.start();
        return valueAnimator;
    }
}
