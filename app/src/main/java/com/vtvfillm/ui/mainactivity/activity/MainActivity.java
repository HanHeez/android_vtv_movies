package com.vtvfillm.ui.mainactivity.activity;

import com.vtvfillm.R;
import com.vtvfillm.base.BaseActivity;
import com.vtvfillm.dagger2.component.AppComponent;
import com.vtvfillm.dagger2.component.DaggerMainComponent;

public class MainActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void initDatas() {
    }

    @Override
    public void configViews() {

    }
}
