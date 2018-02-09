package com.ylf.mygank.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.animation.Animation;

import com.ylf.mygank.interactor.SplashInteractor;
import com.ylf.mygank.interactor.SplashInteractorImpl;
import com.ylf.mygank.view.SplashView;


/**
 * Created by Longfei Yang on 2016/7/22.
 * Email yanglongfei@yuanchuangyun.com
 */
public class SplashPresenterImpl implements Presenter {

    private Context mContext;
    private SplashView mSplashView;
    private SplashInteractor mSplashInteractor;

    public SplashPresenterImpl(Context context, @NonNull SplashView splashView){
        mContext = context;
        mSplashView = splashView;
        mSplashInteractor = new SplashInteractorImpl();
    }

    @Override
    public void initialized() {
        mSplashView.initializeViews(mSplashInteractor.getVersionName(mContext),
                mSplashInteractor.getCopyright(mContext),
                mSplashInteractor.getBackgroundImageResID());
        Animation animation = mSplashInteractor.getBackgroundImageAnimation(mContext);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mSplashView.navigate2MainPage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mSplashView.animateBackgroundImage(animation);
    }

}
