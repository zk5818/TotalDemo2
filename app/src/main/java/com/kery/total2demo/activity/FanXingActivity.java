package com.kery.total2demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kery.total2demo.R;
import com.kery.total2demo.model.IndoImp;

public class FanXingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan_xing);

        IndoImp<Integer, String, String> indoImp = new IndoImp<>(1212);
        Log.d(">>>>>>", "-------------" + indoImp.getData());
        IndoImp.<String>show("21321321");
    }
}
