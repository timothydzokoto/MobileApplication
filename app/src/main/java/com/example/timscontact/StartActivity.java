package com.example.timscontact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    Animation topAnim, botAnim, leftAnim;
    TextView greet1, greet2, dev;
    ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);
        setContentView(R.layout.activity_start);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animator);
        botAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animator);
        leftAnim = AnimationUtils.loadAnimation(this,R.anim.left_animator);

        greet1 = findViewById(R.id.txt_greeting1);
        greet2 = findViewById(R.id.txt_greeting2);
        img1 = findViewById(R.id.image1);
        dev = findViewById(R.id.txt_dev);

        img1.setAnimation(topAnim);
        greet1.setAnimation(topAnim);
        greet2.setAnimation(botAnim);
        dev.setAnimation(leftAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}