package com.vtvfillm.ui.mainactivity.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.open.leanback.widget.GridLayoutManager;
import com.open.leanback.widget.VerticalGridView;
import com.vtvfillm.R;
import com.vtvfillm.base.BaseEpisodeFragment;
import com.vtvfillm.base.MoviesApplication;
import com.vtvfillm.base.OnEpisodeItemClickListener;
import com.vtvfillm.dagger2.component.AppComponent;
import com.vtvfillm.dagger2.component.DaggerMainComponent;
import com.vtvfillm.model.Episode;
import com.vtvfillm.ui.mainactivity.adapter.episode.EpisodeListAdapter;
import com.vtvfillm.ui.mainactivity.contract.EpisodeListContract;
import com.vtvfillm.ui.mainactivity.customview.episode.border.MainUpView;
import com.vtvfillm.ui.mainactivity.presenter.EpisodeListPresent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.bmartel.youtubetv.media.MovieDetail;

public class EpisodeListFragment extends BaseEpisodeFragment implements EpisodeListContract.View, OnEpisodeItemClickListener {

    @BindView(R.id.vertical_grid_view)
    VerticalGridView verticalGridView;
    @BindView(R.id.mainUpView)
    MainUpView mainUpView;

    private Unbinder unbinder;
    private EpisodeListAdapter episodeListAdapter;

    private List<Episode> episodeList;

    @Inject
    EpisodeListPresent episodeListPresent;
    private String id;
    private View view;
    int start = 0;
    int limit = 10;
    MovieDetail movieDetail;
    GridLayoutManager gridLayoutManager;

    private void setupActivityComponent(AppComponent appComponent) {

        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        episodeListPresent.attachView(this);
        Bundle bundle = this.getArguments();
        id = bundle.getString("id");
        movieDetail = (MovieDetail)bundle.getSerializable("customer");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_episode_list, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        setupActivityComponent(MoviesApplication.getsInstance().getAppComponent());
        initData();
        initVerticalGridListener();
        return view;
    }

    private void initVerticalGridListener() {
        episodeListAdapter = new EpisodeListAdapter(episodeList, this,movieDetail);
        episodeListAdapter.setMainView(mainUpView);
        verticalGridView.setNumColumns(5);

        verticalGridView.getBaseGridViewLayoutManager().setFocusOutAllowed(true, true);
        verticalGridView.getBaseGridViewLayoutManager().setFocusOutSideAllowed(false, false);
        verticalGridView.getBaseGridViewLayoutManager().setAutoMeasureEnabled(true);
        verticalGridView.setAdapter(episodeListAdapter);

        verticalGridView.setOnLoadMoreListener(new VerticalGridView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                episodeListPresent.getEpisodeList(id, start, limit);
                verticalGridView.endMoreRefreshComplete();
            }
        });

    }

    private void initData() {
        episodeList = new ArrayList<>();
        episodeListPresent.getEpisodeList(id, start, limit);
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
    public void showEpisodeList(List<Episode> episodeListResult) {
        start = start+1;
        episodeListAdapter.appendData(episodeListResult);
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
