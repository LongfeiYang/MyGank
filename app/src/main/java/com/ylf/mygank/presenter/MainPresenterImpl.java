package com.ylf.mygank.presenter;

import com.ylf.mygank.view.MainView;

/**
 * Created by Longfei Yang on 2016/7/25.
 * Email yanglongfei@yuanchuangyun.com
 */
public class MainPresenterImpl implements Presenter {
    private MainView mMainView;

    public MainPresenterImpl(MainView mainView){
        mMainView = mainView;
    }

    @Override
    public void initialized() {
        mMainView.initializeViews();
    }
}
