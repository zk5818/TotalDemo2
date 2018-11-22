package com.kery.total2demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kery.total2demo.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpFENXIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_fenxi);
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Call call = client.newCall(builder.build());
        client.newCall(builder.build()).request();
        //同步的方法，直接返回Response
        try {
            Response response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //异步的，传入回调CallBack即可(接口，提供onFailure和onResponse方法)
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

        call.cancel();   //取消请求
        call.isExecuted();//是否执行、
        call.isCanceled();//是否取消
        call.clone();//创建一个新的、相同的调用，这个调用可以排队或执行，即使这个调用已经被调用。


        Call.Factory factory=new Call.Factory() {
            @Override
            public Call newCall(Request request) {
                return null;
            }
        };


    }
}
