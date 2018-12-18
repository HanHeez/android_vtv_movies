package com.vtvfillm.ui.mainactivity.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.open.leanback.widget.GridLayoutManager;
import com.open.leanback.widget.OnChildSelectedListener;
import com.open.leanback.widget.VerticalGridView;
import com.vtvfillm.R;
import com.vtvfillm.base.BaseMovieFragment;
import com.vtvfillm.base.MoviesApplication;
import com.vtvfillm.base.OnEpisodeItemClickListener;
import com.vtvfillm.dagger2.component.AppComponent;
import com.vtvfillm.dagger2.component.DaggerMainComponent;
import com.vtvfillm.model.Movie;
import com.vtvfillm.ui.mainactivity.adapter.subcategory.SubCategoryListAdapter;
import com.vtvfillm.ui.mainactivity.contract.SubCategoryContract;
import com.vtvfillm.ui.mainactivity.customview.episode.border.MainUpView;
import com.vtvfillm.ui.mainactivity.presenter.SubCategoryPresent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SubCategoryFragment extends BaseMovieFragment implements SubCategoryContract.View, OnEpisodeItemClickListener {

    @BindView(R.id.vertical_grid_view)
    VerticalGridView verticalGridView;
    @BindView(R.id.mainUpView)
    MainUpView mainUpView;

    private Unbinder unbinder;
    private SubCategoryListAdapter subCategoryListAdapter;

    private List<Movie> movieList;

    @Inject
    SubCategoryPresent subCategoryPresent;
    private String id;
    private View view;
    int start = 0;
    int limit = 15;

    GridLayoutManager gridLayoutManager;

    private void setupActivityComponent(AppComponent appComponent) {

        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        subCategoryPresent.attachView(this);
        Bundle bundle = this.getArguments();
        id = bundle.getString("id");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_subcategory_movie_list, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        setupActivityComponent(MoviesApplication.getsInstance().getAppComponent());
        initData();
        initVerticalGridListener();
        return view;
    }

    private void initVerticalGridListener() {
        subCategoryListAdapter = new SubCategoryListAdapter(movieList, this);
        subCategoryListAdapter.setMainView(mainUpView);
        verticalGridView.setNumColumns(5);

        verticalGridView.getBaseGridViewLayoutManager().setFocusOutAllowed(true, true);
        verticalGridView.getBaseGridViewLayoutManager().setFocusOutSideAllowed(false, false);
        verticalGridView.getBaseGridViewLayoutManager().setAutoMeasureEnabled(true);
//        horizontalGridView.setVerticalMargin(getResources().getDimensionPixelOffset(R.dimen.w_20));
        verticalGridView.setAdapter(subCategoryListAdapter);

        verticalGridView.setOnLoadMoreListener(new VerticalGridView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                int position = verticalGridView.getSelectedPosition();
                subCategoryPresent.getMovieList(id, start, limit);
                verticalGridView.endMoreRefreshComplete();
            }
        });

        verticalGridView.getBaseGridViewLayoutManager().setOnChildSelectedListener(new OnChildSelectedListener() {
            @Override
            public void onChildSelected(ViewGroup parent, View view, int position, long id) {

            }
        });

        verticalGridView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    recyclerView.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {

//                        }
//                    }, 100);
                } else {
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void initData() {
        movieList = new ArrayList<>();
        subCategoryPresent.getMovieList(id, start, limit);
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
    public void showMovieList(List<Movie> movieListResult) {

//        for (int i = 0; i < movieListResult.size(); i++) {
//            movieList.add(movieListResult.get(i));
//        }
        start = start+1;
        subCategoryListAdapter.appendData(movieListResult);
//        subCategoryListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void onEpisodeItemClick(View view, Object item, int adapterPosition) {

    }

}
