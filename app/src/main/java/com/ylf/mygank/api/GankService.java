package com.ylf.mygank.api;

import com.ylf.mygank.entity.Gank;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Longfei Yang on 2016/7/25.
 * Email yanglongfei@yuanchuangyun.com
 */
public interface GankService {
    @GET("api/data/{type}/{count}/{page}")
    Observable<Gank> getGanHuo(
            @Path("type") String type,
            @Path("count") int count,
            @Path("page") int page
    );
}
