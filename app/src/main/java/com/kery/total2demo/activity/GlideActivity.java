package com.kery.total2demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kery.total2demo.R;
import com.kery.total2demo.wight.ExpandTextView;

public class GlideActivity extends AppCompatActivity {
    ImageView iv;
    Button bt;
    ExpandTextView foldTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        iv = (ImageView) findViewById(R.id.iv);
        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(event -> doClick());
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Glide.with(GlideActivity.this)
//                        .load("https://cn.bing.com/s/hpb/NorthMale_EN-US8782628354_1920x1080.jpg")
////                        .asGif()  //实现必须加载动态图片的功能
////                        .asBitmap()//不需要Glide去帮我们自动进行图片格式的判断了。
//                        .centerCrop()
//                        .placeholder(R.drawable.splash)
//                        .error(R.mipmap.ic_launcher)
//                        .override(100, 100)
//                        .into(iv);
//            }
//        });

        foldTextView = (ExpandTextView) findViewById(R.id.text2);
        foldTextView.setText("111111123阿斯顿发阿斯顿发送到大。厦法定阿萨【德法师打发斯蒂芬撒地】方阿萨德法师打发斯问问蒂芬撒地方阿萨德法师打发斯蒂。芬撒地方发送到发送到发送到发送到发送到发送，到发送到发送到发送到，发送111111123阿斯顿发阿斯顿发送到大。厦法定阿萨【德法师打发斯蒂芬撒地】方阿萨德法师打发斯问问蒂芬撒地方阿萨德法师打发斯蒂。芬撒地方发送到发送到发送到发送到发送到发送，到发送到发送到发送到，发送");
//        foldTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "textView点击事件", Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    void doClick(){
        Glide.with(GlideActivity.this)
                .load("https://cn.bing.com/s/hpb/NorthMale_EN-US8782628354_1920x1080.jpg")
//                        .asGif()  //实现必须加载动态图片的功能
//                        .asBitmap()//不需要Glide去帮我们自动进行图片格式的判断了。
                .centerCrop()
                .placeholder(R.drawable.splash)
                .error(R.mipmap.ic_launcher)
                .override(100, 100)
                .into(iv);
    }
}
