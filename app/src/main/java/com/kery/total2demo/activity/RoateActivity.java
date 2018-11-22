package com.kery.total2demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.kery.total2demo.R;

public class RoateActivity extends AppCompatActivity {
    ImageView img;

    boolean isXie = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roate);
        img = (ImageView) findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation rotate = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotate_anim);
                rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
                if (rotate != null) {
                    img.startAnimation(rotate);
                } else {
                    img.setAnimation(rotate);
                    img.startAnimation(rotate);
                }
            }
        });
    }
}
