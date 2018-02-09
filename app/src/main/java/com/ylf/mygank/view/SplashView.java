package com.ylf.mygank.view;

import android.view.animation.Animation;

/**
 * Created by Longfei Yang on 2016/7/22.
 * Email yanglongfei@yuanchuangyun.com
 */
public interface SplashView {
    void initializeViews(String version, String copyright, int backgroundResId);
    void animateBackgroundImage(Animation animation);
    void navigate2MainPage();
}
