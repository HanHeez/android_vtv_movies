package com.vtvfillm.ui.mainactivity.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.app.HeadersFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.PageRow;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v17.leanback.widget.Row;

import com.vtvfillm.R;
import com.vtvfillm.base.MoviesApplication;
import com.vtvfillm.dagger2.component.AppComponent;
import com.vtvfillm.dagger2.component.DaggerMainComponent;
import com.vtvfillm.model.Category;
import com.vtvfillm.model.CategoryDetail;
import com.vtvfillm.ui.mainactivity.activity.SearchActivity;
import com.vtvfillm.ui.mainactivity.customview.header.CustomHeaderItemPresenter;
import com.vtvfillm.ui.mainactivity.contract.MainContract;
import com.vtvfillm.ui.mainactivity.customview.header.CustomHeaderItem;
import com.vtvfillm.factory.GlideBackgroundManager;
import com.vtvfillm.ui.mainactivity.presenter.MainPresent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.support.v17.leanback.widget.BaseGridView.WINDOW_ALIGN_HIGH_EDGE;

public class MainFragment extends BrowseFragment implements MainContract.View  {
    private BackgroundManager mBackgroundManager;
    GlideBackgroundManager glideBackgroundManager;
    HeadersFragment headersFragment = new HeadersFragment();
    List<Category> categoryList = new ArrayList<>();
    @Inject
    MainPresent mainPresent;
    private ArrayObjectAdapter mRowsAdapter;

    private void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        mainPresent.attachView(this);
        glideBackgroundManager = new GlideBackgroundManager(getActivity());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(MoviesApplication.getsInstance().getAppComponent());
        mBackgroundManager = BackgroundManager.getInstance(getActivity());
        mBackgroundManager.attach(getActivity().getWindow());
        mBackgroundManager.setDrawable(getResources().getDrawable(R.drawable.main_background_wide));
        //Static header
        setupUi();
        loadData();
    }

    private void setupUi() {
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);
        headersFragment = getHeadersFragment();

        setBrandColor(getResources().getColor(R.color.header_background_color));
        // set Presenter for Custom Header
        setHeaderPresenterSelector(new PresenterSelector() {
            @Override
            public Presenter getPresenter(Object item) {
                return new CustomHeaderItemPresenter();
            }
        });
        //TODO: goto SearchActivity
        setOnSearchClickedListener(view -> {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        });

    }

    private void loadData() {
        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        setAdapter(mRowsAdapter);

        new Handler().postDelayed(() -> {
            createRows();

        }, 0);
    }

    private void createRows() {
        mainPresent.getCategoryList();
    }

    @Override
    public void showCategoryList(List<Category> categories) {
        categoryList = categories;
        for (int i=0;i<categories.size();i++) {
            CustomHeaderItem headerItem = new CustomHeaderItem(i, categories.get(i).getName());
            PageRow pageRow = new PageRow(headerItem);
            mRowsAdapter.add(pageRow);
        }
        getMainFragmentRegistry().registerFragment(PageRow.class,
                new PageRowFragmentFactory(mBackgroundManager));
        //static Header
        getHeadersFragment().getVerticalGridView().setWindowAlignment(WINDOW_ALIGN_HIGH_EDGE);
    }

    @Override
    public void showSubCategoryList(List<Category> subCategoryList) {

    }


    private class PageRowFragmentFactory extends BrowseFragment.FragmentFactory {
        private final BackgroundManager mBackgroundManager;

        PageRowFragmentFactory(BackgroundManager backgroundManager) {
            this.mBackgroundManager = backgroundManager;
        }

        @Override
        public Fragment createFragment(Object rowObj) {
            Row row = (Row) rowObj;
            MovieFragment movieFragment = new MovieFragment();
            Bundle args = new Bundle();
            args.putString("id", categoryList.get((int) row.getHeaderItem().getId()).getId());
            args.putString("name", categoryList.get((int) row.getHeaderItem().getId()).getName());
            movieFragment.setArguments(args);
            return movieFragment;
        }
    }

    public static class PageFragmentAdapterImpl extends MainFragmentAdapter<MovieFragment> {

        public PageFragmentAdapterImpl(MovieFragment fragment) {
            super(fragment);
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mBackgroundManager.setDrawable(getResources().getDrawable(R.drawable.main_background_wide));
        getHeadersFragment().getVerticalGridView().setWindowAlignment(WINDOW_ALIGN_HIGH_EDGE);
    }
}
