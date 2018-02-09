package com.ylf.mygank.interactor;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.ylf.mygank.R;

import java.util.Calendar;

/**
 * Created by Longfei Yang on 2016/7/22.
 * Email yanglongfei@yuanchuangyun.com
 */
public class SplashInteractorImpl implements SplashInteractor {

    @Override
    public int getBackgroundImageResID() {
        int resId;
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour <= 12) {
            resId = R.mipmap.morning;
        } else if (hour > 12 && hour <= 18) {
            resId = R.mipmap.afternoon;
        } else {
            resId = R.mipmap.night;
        }
        return resId;
    }

    @Override
    public String getCopyright(Context context) {
        return context.getString(R.string.splash_copyright);
    }

    @Override
    public String getVersionName(Context context) {
        String versionName = null;
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return String.format(context.getString(R.string.splash_version), versionName);
    }

    @Override
    public Animation getBackgroundImageAnimation(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.splash);
    }

}
