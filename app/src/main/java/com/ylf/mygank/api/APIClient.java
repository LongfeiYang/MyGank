package com.ylf.mygank.api;

import com.ylf.mygank.entity.Gank;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Longfei Yang on 2016/7/25.
 * Email yanglongfei@yuanchuangyun.com
 */
public class APIClient {
    private static final String GANK_BASE_URL = "http://gank.io/";
    private static Retrofit mRetrofit;

    public static Retrofit getRetrofit(){
        if (mRetrofit == null){
            synchronized (APIClient.class){
                if (mRetrofit == null){
                    mRetrofit = new Retrofit.Builder()
                            .baseUrl(GANK_BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
        }
        return mRetrofit;
    }

    public static void getGank(String type, int count, int page, final APICallBack callBack){
        APIClient.getRetrofit()
                .create(GankService.class)
                .getGanHuo(type, count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Gank>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e);
                    }

                    @Override
                    public void onNext(Gank gank) {
                        callBack.onSuccess(gank.getResults());
                    }
                });

    }

}
