package com.kery.total2demo.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/8/21.
 */

public class MyUrl {
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


}
