package com.vtvfillm.ui.mainactivity.fragment;

import android.os.Bundle;
import android.support.v17.leanback.app.VideoFragment;
import android.util.DisplayMetrics;

import com.vtvfillm.base.MoviesApplication;
import com.vtvfillm.dagger2.component.AppComponent;
import com.vtvfillm.dagger2.component.DaggerMainComponent;
import com.vtvfillm.factory.GlideBackgroundManager;
import com.vtvfillm.model.Episode;

public class PlayVideoFragment extends VideoFragment {   
    private Episode episode;

    private void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
//        movieDetailPresent.attachView(this);
        Bundle bundle = this.getArguments();
        episode = (Episode) bundle.getSerializable("episode");        
//        glideBackgroundManager = new GlideBackgroundManager(getActivity());
//        glideBackgroundManager.loadImage(screenShot);
//        mMetrics = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mMetrics);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(MoviesApplication.getsInstance().getAppComponent());
        setupUi();
        setupEventListeners();
    }

    private void setupEventListeners() {
    }

    private void setupUi() {
    }
}
