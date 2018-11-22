package com.kery.total2demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kery.total2demo.R;
import com.kery.total2demo.wight.CircleLoadingView;

public class RoundProcessActivity extends AppCompatActivity {
    CircleLoadingView circleLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_process);
        circleLoadingView = (CircleLoadingView) findViewById(R.id.do_action);
        circleLoadingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circleLoadingView.reRoad();
            }
        });
    }
}
