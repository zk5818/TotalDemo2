package com.kery.total2demo.model;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/8/21.
 */

public interface MovieService {
    //获取豆瓣Top250 榜单
    @GET("top250")
    Observable<MovieSubject> getTop250 (@Query("start") int start , @Query("count") int count);

    @FormUrlEncoded
    @POST("top250")
    Call<MovieSubject> getTop20(@Field("start") int start, @Field("count") int count);

}
