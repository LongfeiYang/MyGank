package com.ylf.mygank.ui;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylf.mygank.R;
import com.ylf.mygank.base.BaseAppCompatActivity;
import com.ylf.mygank.netstatus.NetUtils;
import com.ylf.mygank.presenter.Presenter;
import com.ylf.mygank.presenter.SplashPresenter;
import com.ylf.mygank.presenter.SplashPresenterImpl;
import com.ylf.mygank.view.SplashView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 闪屏页
 */
public class SplashAct extends AppCompatActivity implements SplashView {

    @BindView(R.id.splash_version_name)
    TextView mVersion;

    @BindView(R.id.splash_copyright)
    TextView mCopyright;

    @BindView(R.id.splash_image)
    ImageView mSplashImage;

    private Presenter mSplashPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.act_splash);

        mSplashPresenter = new SplashPresenterImpl(this, this);
        ButterKnife.bind(this);

        mSplashPresenter.initialized();
    }

    @Override
    public void initializeViews(String version, String copyright, int backgroundResId) {
        mVersion.setText(version);
        mCopyright.setText(copyright);
        mSplashImage.setImageResource(backgroundResId);
    }

    @Override
    public void animateBackgroundImage(Animation animation) {
        mSplashImage.startAnimation(animation);
    }

    @Override
    public void navigate2MainPage() {
        startActivity(MainActivity.newIntent(this));
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == event.getKeyCode()){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
