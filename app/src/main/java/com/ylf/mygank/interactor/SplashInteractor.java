package com.ylf.mygank.interactor;

import android.content.Context;
import android.view.animation.Animation;

/**
 * Created by Longfei Yang on 2016/7/22.
 * Email yanglongfei@yuanchuangyun.com
 */
public interface SplashInteractor {
    int getBackgroundImageResID();

    String getCopyright(Context context);

    String getVersionName(Context context);

    Animation getBackgroundImageAnimation(Context context);
}
