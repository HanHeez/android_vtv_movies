package com.vtvfillm.ui.mainactivity.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.open.leanback.widget.VerticalGridView;
import com.vtvfillm.R;
import com.vtvfillm.base.BaseFragment;
import com.vtvfillm.base.MoviesApplication;
import com.vtvfillm.dagger2.component.AppComponent;
import com.vtvfillm.dagger2.component.DaggerMainComponent;
import com.vtvfillm.factory.GlideBackgroundManager;
import com.vtvfillm.model.Episode;
import com.vtvfillm.model.HomeCategory;
import com.vtvfillm.ui.mainactivity.adapter.episode.EpisodeListAdapter;
import com.vtvfillm.ui.mainactivity.adapter.home.HomeCategoryAdapter;
import com.vtvfillm.ui.mainactivity.contract.HomeContract;
import com.vtvfillm.ui.mainactivity.contract.MovieDetailContract;
import com.vtvfillm.ui.mainactivity.customview.episode.border.MainUpView;
import com.vtvfillm.ui.mainactivity.presenter.HomePresent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {

    @BindView(R.id.vertical_grid_view)
    VerticalGridView verticalGridView;
    @BindView(R.id.mainUpView)
    MainUpView mainUpView;

    @Inject
    HomePresent homePresent;
    View view;

    HomeCategoryAdapter homeCategoryAdapter;
    List<HomeCategory> homeCategoryList = new ArrayList<>();

    private void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        homePresent.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setupActivityComponent(MoviesApplication.getsInstance().getAppComponent());

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
        }
        ButterKnife.bind(this, view);
        initData();
        initVerticalGridListener();
        return view;
    }

    private void initVerticalGridListener() {
//        homeCategoryAdapter = new HomeCategoryAdapter(homeCategoryList, this);
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
    public void onDestroyView() {
        super.onDestroyView();
        if (null != view) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    @Override
    public void refreshRecyclerUi() {
        if (verticalGridView != null) {
            verticalGridView.scrollToPosition(0);
        }
    }


    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

}
