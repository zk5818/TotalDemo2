package com.kery.total2demo.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.kery.total2demo.R;

public class IntentAndFilterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_and_filter);
    }

    //打开一个数据视图，但是没有要求，系统会默认让你从多个中选择其中一个打开
    public void action(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        startActivity(intent);
    }

    //打开一个数据视图，有数据要求，系统帮你打开一个浏览器，并连接到相关的页面
    public void actionUri(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.baidu.com"));
        startActivity(intent);
    }

    //使用显示跳转的方法跳转到第二个页面
    public void ToSecondActivity(View view) {
        Intent intent = new Intent(this, RoundProcessActivity.class);
        startActivity(intent);
    }

    //这里只用自己定义的Action字符串来找到对应的页面
    public void MyAction(View v) {
        Intent intent = new Intent("HelloWorld");//Action字符串可以直接放在Intent的构造函数里面，也可以单独写
        // intent.setAction("HelloWorld");
        intent.addCategory("android.intent.category.DEFAULT");//可以没有
        //但是自定义的Action的xml文件里面必须要有category元素，否则会报错
        startActivity(intent);
    }

    //打开程序入口，特征是桌面
    public void actionMain(View v) {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "找不到目标页面", Toast.LENGTH_SHORT).show();
        }

    }

}
