package com.kery.total2demo.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kery.total2demo.R;

public class RunPermissionActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_permission);
        Button bt_call = (Button) findViewById(R.id.button);
        bt_call.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //其实就是6.0版本价格判断，再获取下权限 没啥东西
        if (ContextCompat.checkSelfPermission(RunPermissionActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(RunPermissionActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            call();
        }
    }


    private void call() {
    /*为了防止崩溃，我们用异常捕获代码块捕获异常，startActivity(intent)报错，让添加安全权限**/
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(this, "你拒绝了这个权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}
