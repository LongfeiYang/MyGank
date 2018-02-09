package com.ylf.mygank;

import android.app.Application;
import android.content.Context;

/**
 * Created by Longfei Yang on 2016/11/21.
 * Email yanglongfei@yuanchuangyun.com
 */

public class APP extends Application {
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public Context getContext(){
        if (context == null) {
            context = this;
        }
        return context;
    }

}
