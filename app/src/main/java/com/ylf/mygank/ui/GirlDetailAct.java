package com.ylf.mygank.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ylf.mygank.R;
import com.ylf.mygank.base.BaseAppCompatActivity;
import com.ylf.mygank.netstatus.NetUtils;
import com.ylf.mygank.presenter.GirlPresenter;
import com.ylf.mygank.presenter.GirlPresenterImpl;
import com.ylf.mygank.view.GirlView;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Longfei Yang on 2016/7/26.
 * Email yanglongfei@yuanchuangyun.com
 */
public class GirlDetailAct extends BaseAppCompatActivity implements GirlView {
    @BindView(R.id.girl_toolbar)
    Toolbar mToolBar;
    @BindView(R.id.girl_image)
    ImageView mGirl;

    private String mDesc;
    private String mUrl;
    private Bitmap mBitmap;
    private GirlPresenter mPresenter;
    private PhotoViewAttacher mPhotoViewAttacher;

    public static Intent newIntent(Context context, String desc, String url){
        Intent intent = new Intent(context, GirlDetailAct.class);
        Bundle bundle = new Bundle();
        bundle.putString("desc", desc);
        bundle.putString("url", url);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.act_girl_detail);
        ButterKnife.bind(this);

        mPresenter = new GirlPresenterImpl(this, this);
        mPresenter.initialized();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.act_girl_detail;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return true;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.RIGHT;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        mDesc = bundle.getString("desc");
        mUrl = bundle.getString("url");
    }

    @Override
    public void initializeViews() {
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
        getSupportActionBar().setTitle(mDesc);

        mPhotoViewAttacher = new PhotoViewAttacher(mGirl);
        Glide.with(this)//返回相应的RequestManager
                .load(mUrl)//返回DrawableTypeRequest<String>
                .asBitmap()//返回BitmapTypeRequest<String>
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)//返回BitmapRequestBuilder
                .into(new SimpleTarget<Bitmap>() {//加入执行队列
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        mGirl.setImageBitmap(resource);
                        mPhotoViewAttacher.update();
                        mBitmap = resource;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_girl,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_share:
                mPresenter.shareImage(mUrl, mBitmap, mGirl);
                break;
            case R.id.action_save:
                mPresenter.saveImage(mUrl, mBitmap, mGirl);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}