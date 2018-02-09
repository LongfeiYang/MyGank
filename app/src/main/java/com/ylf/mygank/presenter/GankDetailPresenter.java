package com.ylf.mygank.presenter;

import android.view.View;

/**
 * Created by Longfei Yang on 2016/7/26.
 * Email yanglongfei@yuanchuangyun.com
 */
public interface GankDetailPresenter extends Presenter{
    void shareGank(String url);
    void copyLink(String url, View view);
    void openByOtherBrowser(String url);
}
