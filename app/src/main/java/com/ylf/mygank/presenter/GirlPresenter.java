package com.ylf.mygank.presenter;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by Longfei Yang on 2016/7/26.
 * Email yanglongfei@yuanchuangyun.com
 */
public interface GirlPresenter extends Presenter {
    void shareImage(String url, Bitmap bitmap, ImageView imageView);
    void saveImage(String url, Bitmap bitmap, ImageView imageView);
}
