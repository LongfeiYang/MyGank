package com.ylf.mygank.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ylf.mygank.R;
import com.ylf.mygank.base.BaseAppCompatActivity;
import com.ylf.mygank.netstatus.NetUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 关于
 * Created by Longfei Yang on 2016/7/26.
 * Email yanglongfei@yuanchuangyun.com
 */
public class AboutAct extends BaseAppCompatActivity {

    @BindView(R.id.about_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.about_toolbar)
    Toolbar toolbar;

    public static Intent newIntent(Context context){
        return new Intent(context, AboutAct.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.act_about);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setTitle("关于");

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_about;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
