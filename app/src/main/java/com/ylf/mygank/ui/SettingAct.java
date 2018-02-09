package com.ylf.mygank.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.ylf.mygank.R;
import com.ylf.mygank.base.BaseAppCompatActivity;
import com.ylf.mygank.base.BaseAppManager;
import com.ylf.mygank.netstatus.NetUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置
 * Created by Longfei Yang on 2016/7/27.
 * Email yanglongfei@yuanchuangyun.com
 */
public class SettingAct extends BaseAppCompatActivity {
    @BindView(R.id.setting_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.setting_clear_cache)
    Button mCleanCache;
    @BindView(R.id.setting_exit)
    Button mExit;

    public static Intent newIntent(Context context){
        return new Intent(context, SettingAct.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.act_setting);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
        getSupportActionBar().setTitle("设置");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_setting;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @OnClick(R.id.setting_clear_cache)
    public void cleanCache(View view){
        Snackbar.make(view, "Clean cache 10 M", Snackbar.LENGTH_SHORT).show();
    }

    @OnClick(R.id.setting_exit)
    public void exit(View view){
        Snackbar.make(view, "退出", Snackbar.LENGTH_SHORT).show();
        BaseAppManager.getInstance().clear();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
