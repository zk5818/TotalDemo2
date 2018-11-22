package com.kery.total2demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kery.total2demo.R;
import com.kery.total2demo.model.MovieService;
import com.kery.total2demo.model.MovieSubject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRxJavaOkHttpActivity extends AppCompatActivity {
    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.bt)
    Button bt;
    private Button button;
    final String string = "dsldsd";

    @OnClick({R.id.bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt:
                //okhttp3
                OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
                builder.readTimeout(10, TimeUnit.SECONDS);
                builder.connectTimeout(9, TimeUnit.SECONDS);
                //retrofit
                Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                        .client(builder.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
                //rxjava
                MovieService api = retrofit.create(MovieService.class);
                Observable<MovieSubject> observable = api.getTop250(0, 10);
                observable.subscribeOn(Schedulers.io())
                        .flatMap(new Function<MovieSubject, ObservableSource<MovieSubject.SubjectsBean>>() {
                            @Override
                            public ObservableSource<MovieSubject.SubjectsBean> apply(MovieSubject city) throws Exception {
                                List<MovieSubject.SubjectsBean> result = city.getSubjects();
                                return Observable.fromIterable(result);
                            }
                        })
                        .filter(new Predicate<MovieSubject.SubjectsBean>() {
                            @Override
                            public boolean test(MovieSubject.SubjectsBean city) throws Exception {
                                String id = city.getId();
                                return true;
                            }
                        })
                        .take(5)
                        .doOnNext(new Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Exception {
                                System.out.println("准备工作");
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<MovieSubject.SubjectsBean>() {
                            @Override
                            public void accept(MovieSubject.SubjectsBean city) throws Exception {
//                                System.out.println(city.getId());
                                tv.setText(tv.getText().toString() + "\n" + city.getId());
                            }
                        });

                break;
            default:

                break;
        }
    }

    public static final String BASE_URL = "https://api.douban.com/v2/movie/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_rx_java_ok_http);
        ButterKnife.bind(this);

//        final Retrofit retrofit = new Retrofit.Builder()
//
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//        final MovieService movieService = retrofit.create(MovieService.class);

//                //获取接口实例
//                final MovieService movieService = retrofit.create(MovieService.class);
//                //调用方法得到一个Call
//                Call<MovieSubject> call = movieService.getTop20(0, 10);
//                //进行网络请求
//                call.enqueue(new Callback<MovieSubject>() {
//                    @Override
//                    public void onResponse(Call<MovieSubject> call, Response<MovieSubject> response) {
//                        tv.setText(response.body().toString());
//                        //                        mMovieAdapter.setMovies(response.body().subjects);
////                        mMovieAdapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onFailure(Call<MovieSubject> call, Throwable t) {
//                        t.printStackTrace();
//                    }
//                });


//        Subscription subscription = movieService.getTop250(0, 20)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableObserver<MovieSubject>() {
//                    @Override
//                    public void onComplete() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(MovieSubject movieSubject) {
//                        mMovieAdapter.setMovies(movieSubject.subjects);
//                        mMovieAdapter.notifyDataSetChanged();
//                    }
//                });
    }
}
