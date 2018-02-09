package com.ylf.mygank.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.ylf.mygank.R;
import com.ylf.mygank.base.BaseAppCompatActivity;
import com.ylf.mygank.netstatus.NetUtils;
import com.ylf.mygank.presenter.GankDetailPresenter;
import com.ylf.mygank.presenter.GankDetailPresenterImpl;
import com.ylf.mygank.util.WebViewConfigUtil;
import com.ylf.mygank.view.GankDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Longfei Yang on 2016/7/26.
 * Email yanglongfei@yuanchuangyun.com
 */
public class GankDetailAct extends BaseAppCompatActivity implements GankDetailView {

    @BindView(R.id.gank_detail_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.gank_detail_web)
    WebView mWebView;
    @BindView(R.id.gank_detail_progress)
    ProgressBar mProgressBar;

    private String mUrl;
    private String mDesc;

    private GankDetailPresenter mPresenter;

    public static Intent newIntent(Context context, String desc, String url){
        Intent intent = new Intent(context, GankDetailAct.class);
        Bundle bundle = new Bundle();
        bundle.putString("desc", desc);
        bundle.putString("url", url);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.act_gank_detail);

        ButterKnife.bind(this);
        mPresenter = new GankDetailPresenterImpl(this, this);
        mPresenter.initialized();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWebView != null){
            mWebView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWebView != null){
            mWebView.onPause();
        }
    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        mDesc = bundle.getString("desc");
        mUrl = bundle.getString("url");
    }

    @Override
    public void initializeViews() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
        getSupportActionBar().setTitle(mDesc);

        WebViewConfigUtil.config(mWebView, mProgressBar);
        mWebView.loadUrl(mUrl);
    }

    @Override
    public void navigateToOtherWebView(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gank_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:{
                finish();
                break;
            }
            case R.id.action_share:
                mPresenter.shareGank(mUrl);
                break;
            case R.id.action_copy_url:
                mPresenter.copyLink(mUrl, mWebView);
                break;
            case R.id.action_open_in_browser:
                mPresenter.openByOtherBrowser(mUrl);
                break;
            case R.id.action_refresh:
                mWebView.reload();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null){
            mWebView.removeAllViews();
            mWebView.destroy();
        }
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_gank_detail;
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

}
