package com.ylf.mygank.ui;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ylf.mygank.R;
import com.ylf.mygank.base.BaseAppCompatActivity;
import com.ylf.mygank.netstatus.NetUtils;
import com.ylf.mygank.presenter.MainPresenterImpl;
import com.ylf.mygank.presenter.Presenter;
import com.ylf.mygank.view.MainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页面
 */
public class MainActivity extends BaseAppCompatActivity implements MainView {

    @BindView(R.id.main_tabs)
    TabLayout mTab;
    @BindView(R.id.main_fab)
    FloatingActionButton mFAB;
    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.content_view_pager)
    ViewPager mViewPager;

    private List<Fragment> mFragList;
    private String[] titles = {"Android", "iOS", "前端", "福利"};
    //    private String[] titles = {"Android", "iOS", "前端", "福利", "休息视频", "拓展资源"};
    private Presenter presenter;

    public static Intent newIntent(Context context){
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.act_main);

        presenter = new MainPresenterImpl(this);
        presenter.initialized();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_main;
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
    public void initializeViews() {
        mFragList = new ArrayList<>();
        for (String title : titles) {
            if ("福利".equals(title)){
                mFragList.add(GirlFrag.newInstance(title));
            }else{
                mFragList.add(GankFrag.newInstance(title));
            }
        }

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragList.get(position);
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
            @Override
            public int getCount() {
                return mFragList.size();
            }
        });
        mTab.setupWithViewPager(mViewPager);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startService(new Intent(MainActivity.this, LocalService.class));
//                startService(new Intent(MainActivity.this, RemoteService.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_app_setting:{
                readyGo(SettingAct.class);
                break;
            }
            case R.id.action_about_app:{
                readyGo(AboutAct.class);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
