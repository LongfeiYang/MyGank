package com.ylf.mygank.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.ylf.mygank.util.ImageUtil;
import com.ylf.mygank.util.ShareUtil;
import com.ylf.mygank.view.GirlView;

/**
 * Created by Longfei Yang on 2016/7/25.
 * Email yanglongfei@yuanchuangyun.com
 */
public class GirlPresenterImpl implements GirlPresenter {
    private Context mContext;
    private GirlView mGirlView;

    public GirlPresenterImpl(@NonNull Context context, GirlView girlView){
        mContext = context;
        mGirlView = girlView;
    }

    @Override
    public void initialized() {
        mGirlView.initData();
        mGirlView.initializeViews();
    }

    public void shareImage(String url, Bitmap bitmap, ImageView imageView){
        ShareUtil.shareImage(mContext, ImageUtil.getShareUri(mContext, url, bitmap, imageView));
    }

    @Override
    public void saveImage(String url, Bitmap bitmap, ImageView imageView) {
        ImageUtil.saveImage(mContext, url, bitmap, imageView);
    }

}
