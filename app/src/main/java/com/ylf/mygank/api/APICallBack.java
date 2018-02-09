package com.ylf.mygank.api;

/**
 * Created by Longfei Yang on 2016/7/25.
 * Email yanglongfei@yuanchuangyun.com
 */
public interface APICallBack<T> {
    void onSuccess(T t);
    void onError(Throwable e);
}
