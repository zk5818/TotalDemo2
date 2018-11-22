package com.kery.total2demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.kery.total2demo.activity.Camera2Activity;
import com.kery.total2demo.activity.Camera3Activity;
import com.kery.total2demo.activity.CameraMakeActivity;
import com.kery.total2demo.activity.ChartActivity;
import com.kery.total2demo.activity.ChenJinShiActivity;
import com.kery.total2demo.activity.ClickEventActivity;
import com.kery.total2demo.activity.DanMuActivity;
import com.kery.total2demo.activity.FanXingActivity;
import com.kery.total2demo.activity.GlideActivity;
import com.kery.total2demo.activity.IntentAndFilterActivity;
import com.kery.total2demo.activity.JavaBaseActivity;
import com.kery.total2demo.activity.OkHttpFENXIActivity;
import com.kery.total2demo.activity.RetrofitRxJavaOkHttpActivity;
import com.kery.total2demo.activity.RoateActivity;
import com.kery.total2demo.activity.RoundProcessActivity;
import com.kery.total2demo.activity.RunPermissionActivity;
import com.kery.total2demo.activity.WebViewActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10,
            tv11, tv12, tv13, tv14, tv15, tv16, tv17, tv18, tv19, tv20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    void initView() {
        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setOnClickListener(this);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv2.setOnClickListener(this);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv3.setOnClickListener(this);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv4.setOnClickListener(this);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv5.setOnClickListener(this);
        tv6 = (TextView) findViewById(R.id.tv6);
        tv6.setOnClickListener(this);
        tv7 = (TextView) findViewById(R.id.tv7);
        tv7.setOnClickListener(this);
        tv8 = (TextView) findViewById(R.id.tv8);
        tv8.setOnClickListener(this);
        tv9 = (TextView) findViewById(R.id.tv9);
        tv9.setOnClickListener(this);
        tv10 = (TextView) findViewById(R.id.tv10);
        tv10.setOnClickListener(this);
        tv11 = (TextView) findViewById(R.id.tv11);
        tv11.setOnClickListener(this);
        tv12 = (TextView) findViewById(R.id.tv12);
        tv12.setOnClickListener(this);
        tv13 = (TextView) findViewById(R.id.tv13);
        tv13.setOnClickListener(this);
        tv14 = (TextView) findViewById(R.id.tv14);
        tv14.setOnClickListener(this);
        tv15 = (TextView) findViewById(R.id.tv15);
        tv15.setOnClickListener(this);
        tv16 = (TextView) findViewById(R.id.tv16);
        tv16.setOnClickListener(this);
        tv17 = (TextView) findViewById(R.id.tv17);
        tv17.setOnClickListener(this);
//        ( (CheckBox)findViewById(R.id.cb)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Log.e(">>>>>>>>>","onCheckedChanged");
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1:
                startActivity(new Intent(this, ClickEventActivity.class));
                break;
            case R.id.tv2:
                startActivity(new Intent(this, ChenJinShiActivity.class));
                break;
            case R.id.tv3:
                startActivity(new Intent(this, DanMuActivity.class));
                break;
            case R.id.tv4:
                startActivity(new Intent(this, GlideActivity.class));
                break;
            case R.id.tv5:
                startActivity(new Intent(this, RoateActivity.class));
                break;
            case R.id.tv6:
                startActivity(new Intent(this, WebViewActivity.class));
                break;
            case R.id.tv7:
                startActivity(new Intent(this, RunPermissionActivity.class));
                break;
            case R.id.tv8:
                startActivity(new Intent(this, RetrofitRxJavaOkHttpActivity.class));
                break;
            case R.id.tv9:
                startActivity(new Intent(this, JavaBaseActivity.class));
                break;
            case R.id.tv10:
                startActivity(new Intent(this, OkHttpFENXIActivity.class));
                break;
            case R.id.tv11:
                startActivity(new Intent(this, RoundProcessActivity.class));
                break;
            case R.id.tv12:
                startActivity(new Intent(this, IntentAndFilterActivity.class));
                break;
            case R.id.tv13:
                startActivity(new Intent(this, FanXingActivity.class));
                break;
            case R.id.tv14:
                startActivity(new Intent(this, CameraMakeActivity.class));
                break;
            case R.id.tv15:
                startActivity(new Intent(this, Camera2Activity.class));
                break;
            case R.id.tv16:
                startActivity(new Intent(this, Camera3Activity.class));
                break;
            case R.id.tv17:
                startActivity(new Intent(this, ChartActivity.class));
                break;
        }
    }
}
