package com.kery.total2demo.model;

import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/3.
 */

public class IndoImp<T, K, V> implements Indo<T> {
    private T var;
    private K key1;
    private V v1;

    public IndoImp(T var) {
        this.setData(var);
    }

    @Override
    public T getData() {
        return var;
    }

    @Override
    public void setData(T x) {
        this.var = x;
    }

    public static <T> void show(T a) {
        Log.d(">>>>>>", "-------------" + a.toString());
    }

    public interface Comparable<T> {
        public boolean compareTo(T i);
    }

    public static <T extends Comparable> T min(T... a) {
        T smallest = a[0];
        for (T item : a) {
            if (smallest.compareTo(item)) {
                smallest = item;
            }
        }
        return smallest;
    }

    public static <T> List<T> parseArray(String response, Class<T> object) throws JSONException {
        List<T> list = new ArrayList<>();
        JSONArray array = new JSONArray(response);
        for (int i = 0; i < array.length(); i++) {
            T t = getT(array.getString(i), object);
            if (t != null)
                list.add(t);
        }
//        return list;
        //        List<T> list =Josn.parseArray(response, object);
        return list;
    }

    @Nullable
    private static <T> T getT(String s, Class<T> k) {
        Gson gson = new Gson();
        try {
            T t = gson.fromJson(s, k);
            return t;
        } catch (Exception e) {
//            LogUtil.log("Gson转换失败，不是正常json格式", e.getMessage());
        }
        return null;
    }

}
