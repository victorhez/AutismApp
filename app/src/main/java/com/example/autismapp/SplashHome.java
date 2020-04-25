package com.example.autismapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashHome extends AppCompatActivity {
    private Animation buttonAnim, topAnim;
    private ImageView logo;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_home);
        topAnim = AnimationUtils.loadAnimation(this, R.anim.logo_anim);
        buttonAnim = AnimationUtils.loadAnimation(this, R.anim.text_anim);
        textView = findViewById(R.id.text);
        textView.setAnimation(topAnim);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(),Splash.class));

                    }
                }, 7000);
           }
        });

    }
}
