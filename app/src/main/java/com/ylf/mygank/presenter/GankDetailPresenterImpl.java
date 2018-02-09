package com.ylf.mygank.presenter;

import android.content.Context;
import android.view.View;

import com.ylf.mygank.util.ShareUtil;
import com.ylf.mygank.view.GankDetailView;

/**
 * Created by Longfei Yang on 2016/7/26.
 * Email yanglongfei@yuanchuangyun.com
 */
public class GankDetailPresenterImpl implements GankDetailPresenter {
    private Context mContext;
    private GankDetailView mGankDetailView;

    public GankDetailPresenterImpl(Context context, GankDetailView detailView){
        mContext = context;
        mGankDetailView = detailView;
    }

    @Override
    public void initialized() {
        mGankDetailView.initData();
        mGankDetailView.initializeViews();
    }

    @Override
    public void shareGank(String url) {
        ShareUtil.shareText(mContext, url);
    }

    @Override
    public void copyLink(String url, View view) {
        ShareUtil.copy2Clipboard(mContext, url, view);
    }

    @Override
    public void openByOtherBrowser(String url) {
        mGankDetailView.navigateToOtherWebView(url);
    }

}
