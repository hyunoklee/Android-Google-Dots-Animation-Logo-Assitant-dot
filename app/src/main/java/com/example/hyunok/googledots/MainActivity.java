package com.example.hyunok.googledots;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TranslateAnimation anim;
    Animation slowly_appear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final RelativeLayout iv = (RelativeLayout)findViewById(R.id.windows_relative);
        final com.example.mylibrary.GoogleDotUserSpeaking GoogleDotUserListening =
                (com.example.mylibrary.GoogleDotUserSpeaking)findViewById(R.id.GoogleDotUserListening);
        final com.example.mylibrary.GoogleDotThinking GoogleDotThinking =
                (com.example.mylibrary.GoogleDotThinking)findViewById(R.id.GoogleDotThinking);
        final com.example.mylibrary.GoogleDotListening GoogleDotListening =
                (com.example.mylibrary.GoogleDotListening)findViewById(R.id.GoogleDotListening);
        GoogleDotUserListening.setHeightRatio((float) 0.5);
        GoogleDotListening.setHeightRatio((float) 0.2);

        Button b = (Button)findViewById(R.id.button2);
        final ImageView imageView1 = (ImageView)findViewById(R.id.imageView1);
        anim = new TranslateAnimation
                (0,   // fromXDelta
                        500,  // toXDelta
                        0,    // fromYDelta
                        0);// toYDelta
        anim.setFillAfter(true);

        slowly_appear = AnimationUtils.loadAnimation(this,R.anim.slowly_fadein);
        slowly_appear.setDuration(2000);
        imageView1.setVisibility(View.INVISIBLE);

        b.setOnClickListener(new View.OnClickListener() {
            //@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                iv.animate().setDuration(2000);
                iv.animate().translationX(-500).withLayer().withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        // do something
                        imageView1.setVisibility(View.VISIBLE);
                        imageView1.setAnimation(slowly_appear);
                    }
                }).start();

                //GoogleDotUserListening.stop();
                //GoogleDotThinking.stop();
            }
        });
    }
}
