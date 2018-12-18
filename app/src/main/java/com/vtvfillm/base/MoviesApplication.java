package com.vtvfillm.base;

import android.app.Application;
import android.content.Context;

import com.vtvfillm.dagger2.component.AppComponent;
import com.vtvfillm.dagger2.component.DaggerAppComponent;
import com.vtvfillm.dagger2.module.AppModule;
import com.vtvfillm.dagger2.module.MoviesApiModule;
import com.vtvfillm.utils.AppUtils;
import com.vtvfillm.utils.SharedPreferencesUtil;


public class MoviesApplication extends Application {
    private static MoviesApplication sInstance;
    private AppComponent appComponent;

    public static MoviesApplication getsInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.sInstance = this;
        initComponent();
        initPrefs();
        AppUtils.init(this);
    }

    private void initComponent() {
        appComponent = DaggerAppComponent.builder()
                .moviesApiModule(new MoviesApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    protected void initPrefs() {
        SharedPreferencesUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }

}
