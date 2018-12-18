package com.vtvfillm.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.vtvfillm.dagger2.component.AppComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends Activity {

    public Toolbar mCommonToolbar;
    protected Context mContext;
    protected int statusBarColor = 0;
    protected View statusBarView = null;
    protected Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        mContext = this;
        setupActivityComponent(MoviesApplication.getsInstance().getAppComponent());
        initDatas();
        configViews();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public abstract int getLayoutId();

    protected abstract void setupActivityComponent(AppComponent appComponent);

    public abstract void initDatas();

    public abstract void configViews();

    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

