package com.kery.total2demo.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Intent;

import com.kery.total2demo.MainActivity;

/**
 * Created by Administrator on 2018/8/23.
 */


public class MyApplication extends Application implements Thread.UncaughtExceptionHandler {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this); // 一定要先初始化
        Thread.setDefaultUncaughtExceptionHandler(CrashHandler.getInstance());
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 获取栈中最顶部的Activity，即最后发生崩溃的Activity。
     * 如果你只需要打开MainActivity等固定的Activity则无需使用此方法
     */
    public Class getTopActivity() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        String className = manager.getRunningTasks(1).get(0).topActivity.getClassName();
        Class cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cls;
    }

}
