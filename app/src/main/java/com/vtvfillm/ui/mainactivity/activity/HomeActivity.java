package com.vtvfillm.ui.mainactivity.activity;

import android.app.Activity;
import android.support.v17.leanback.widget.BrowseFrameLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.open.leanback.widget.VerticalGridView;
import com.vtvfillm.R;
import com.vtvfillm.base.MoviesApplication;
import com.vtvfillm.dagger2.component.AppComponent;
import com.vtvfillm.dagger2.component.DaggerMainComponent;
import com.vtvfillm.model.HomeCategory;
import com.vtvfillm.ui.mainactivity.adapter.home.HomeCategoryAdapter;
import com.vtvfillm.ui.mainactivity.contract.HomeContract;
import com.vtvfillm.ui.mainactivity.customview.episode.border.MainUpView;
import com.vtvfillm.ui.mainactivity.fragment.HomeFragment;
import com.vtvfillm.ui.mainactivity.fragment.MovieDetailFragment;
import com.vtvfillm.ui.mainactivity.presenter.HomePresent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends FragmentActivity implements HomeContract.View{

    @BindView(R.id.vertical_grid_view)
    VerticalGridView verticalGridView;
    @BindView(R.id.mainUpView)
    MainUpView mainUpView;

    @Inject
    HomePresent homePresent;

    HomeCategoryAdapter homeCategoryAdapter;
    List<HomeCategory> homeCategoryList = new ArrayList<>();

    private void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        homePresent.attachView(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupActivityComponent(MoviesApplication.getsInstance().getAppComponent());
        ButterKnife.bind(this);
        initData();
        initVerticalGridListener();

    }

    private void initVerticalGridListener() {
        homeCategoryAdapter = new HomeCategoryAdapter(homeCategoryList, this);
        homeCategoryAdapter.setMainView(mainUpView);
        verticalGridView.setNumColumns(5);

        verticalGridView.getBaseGridViewLayoutManager().setFocusOutAllowed(true, true);
        verticalGridView.getBaseGridViewLayoutManager().setFocusOutSideAllowed(false, false);
        verticalGridView.getBaseGridViewLayoutManager().setAutoMeasureEnabled(true);
        verticalGridView.setAdapter(homeCategoryAdapter);


    }

    private void initData() {
        homeCategoryList.add(new HomeCategory("1", "Việt Nam", R.color.vietnam_home_category_color));
        homeCategoryList.add(new HomeCategory("2", "Quốc Tế", R.color.quocte_home_category_color));
        homeCategoryList.add(new HomeCategory("3", "Bom tấ́n", R.color.bomtan_home_category_color));
        homeCategoryList.add(new HomeCategory("4", "Trailer Phim mới", R.color.trailerphimmoi_home_category_color));
        homeCategoryList.add(new HomeCategory("5", "ULTRA 4kHD", R.color.ultra4khd_category_color));
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }
}
