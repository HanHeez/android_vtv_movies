package com.vtvfillm.ui.mainactivity.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v17.leanback.app.DetailsFragment;
import android.support.v17.leanback.app.DetailsFragmentBackgroundController;
import android.support.v17.leanback.widget.Action;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ClassPresenterSelector;
import android.support.v17.leanback.widget.DetailsOverviewLogoPresenter;
import android.support.v17.leanback.widget.DetailsOverviewRow;
import android.support.v17.leanback.widget.FullWidthDetailsOverviewSharedElementHelper;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.vtvfillm.R;
import com.vtvfillm.base.MoviesApplication;
import com.vtvfillm.dagger2.component.AppComponent;
import com.vtvfillm.dagger2.component.DaggerMainComponent;
import com.vtvfillm.factory.GlideBackgroundManager;
import com.vtvfillm.model.Episode;
import com.vtvfillm.model.Movie;
import com.vtvfillm.ui.mainactivity.activity.EpisodeListActivity;
import com.vtvfillm.ui.mainactivity.activity.MovieDetailActivity;
import com.vtvfillm.ui.mainactivity.activity.PlayVideoActivity;
import com.vtvfillm.ui.mainactivity.contract.MovieDetailContract;
import com.vtvfillm.ui.mainactivity.customview.detail.CustomMovieDetailPresenter;
import com.vtvfillm.ui.mainactivity.customview.detail.MovieDetailDescriptionPresenter;
import com.vtvfillm.ui.mainactivity.customview.episode.EpisodePresenterSelector;
import com.vtvfillm.ui.mainactivity.customview.header.CustomHeaderItem;
import com.vtvfillm.ui.mainactivity.customview.movie.MoviePresenterSelector;
import com.vtvfillm.ui.mainactivity.presenter.MovieDetailPresent;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import fr.bmartel.youtubetv.media.MovieDetail;

public class MovieDetailFragment extends DetailsFragment implements MovieDetailContract.View, OnItemViewClickedListener,
        OnItemViewSelectedListener {

    public static final String TRANSITION_NAME = "t_for_transition";

    private static final long ACTION_PLAY = 1;
    private static final long ACTION_EPISODE_LIST = 2;
    private static final long ACTION_RELATED = 3;

    private Action mActionPlay;
    private Action mActionEpisodeList;
    private Action mActionRelated;
    private ArrayObjectAdapter mRowsAdapter;
    private final DetailsFragmentBackgroundController mDetailsBackground =
            new DetailsFragmentBackgroundController(this);
    private String id;
    private String subcategoryId;
    private String screenShot = "";
    ArrayObjectAdapter actionAdapter;

    private DisplayMetrics mMetrics;
    private GlideBackgroundManager glideBackgroundManager;
    private MovieDetail currentMovieDetail;
    SpinnerFragment mSpinnerFragment;
    @Inject
    MovieDetailPresent movieDetailPresent;
    private CustomMovieDetailPresenter customMovieDetailPresenter;
    private ClassPresenterSelector rowPresenterSelector;
    private int selectedPosition = 0;

    private void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        movieDetailPresent.attachView(this);
        Bundle bundle = this.getArguments();
        id = bundle.getString("id");
        subcategoryId = bundle.getString("subcategoryId");
        screenShot = bundle.getString("screenShot");
        if (!screenShot.contains("http")) {
            screenShot = "";
        }
        glideBackgroundManager = new GlideBackgroundManager(getActivity());
        glideBackgroundManager.loadImage(screenShot);
        mMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mMetrics);

    }

    public void changePositionWhenPressBack() {

        if (actionAdapter != null) {
            if (selectedPosition > 1) {
                setSelectedPosition(0);
                selectedPosition = 0;
            } else {
                getActivity().finish();
            }
        }
    }

    public void changePositionWhenPressDown() {
        selectedPosition++;
        if (selectedPosition > 3) {
            selectedPosition = 3;
        }

    }

    public void changePositionWhenPressUp() {
        selectedPosition--;
        if (selectedPosition < 0) {
            selectedPosition = 0;
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(MoviesApplication.getsInstance().getAppComponent());
        setupUi();
        setupEventListeners();
    }

    private void setupUi() {
        //Get data
        mSpinnerFragment = new SpinnerFragment();
        getFragmentManager().beginTransaction().add(R.id.details_fragment, mSpinnerFragment).commit();

        movieDetailPresent.getMovieDetail(id);
        // Setup fragment
//        setTitle(getString(R.string.detail_view_title));

        //TODO: setting custom Detail Here
        customMovieDetailPresenter = new CustomMovieDetailPresenter(new MovieDetailDescriptionPresenter(getActivity()), new DetailsOverviewLogoPresenter());
        customMovieDetailPresenter.setActionsBackgroundColor(ContextCompat.getColor(getActivity(), R.color.transparent_50));
        customMovieDetailPresenter.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.transparent_50));
        FullWidthDetailsOverviewSharedElementHelper mHelper = new FullWidthDetailsOverviewSharedElementHelper();
        mHelper.setSharedElementEnterTransition(getActivity(), TRANSITION_NAME);
        customMovieDetailPresenter.setListener(mHelper);
        customMovieDetailPresenter.setParticipatingEntranceTransition(false);
        prepareEntranceTransition();


        rowPresenterSelector = new ClassPresenterSelector();
        rowPresenterSelector.addClassPresenter(DetailsOverviewRow.class, customMovieDetailPresenter);
        rowPresenterSelector.addClassPresenter(ListRow.class, new ListRowPresenter());
        mRowsAdapter = new ArrayObjectAdapter(rowPresenterSelector);
    }

    private void initializeBackground(Uri uri) {
        mDetailsBackground.enableParallax();
        int width = mMetrics.widthPixels;
        int height = mMetrics.heightPixels;
        Glide.with(getActivity())
                .load(uri)
                .asBitmap()
                .centerCrop()
                .error(R.drawable.bg_default_moviedetail)
                .into(new SimpleTarget<Bitmap>(width, height) {
                    @Override
                    public void onResourceReady(Bitmap resource,
                                                GlideAnimation<? super Bitmap>
                                                        glideAnimation) {
                        mDetailsBackground.setCoverBitmap(resource);
                    }
                });
    }

    private void setupEventListeners() {
        setOnItemViewSelectedListener(this);
        setOnItemViewClickedListener(this);
    }


    //TODO: Setting click to Action Bar
    @Override
    public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                              RowPresenter.ViewHolder rowViewHolder, Row row) {
        if ((item instanceof Action)) {
            Action action = (Action) item;
            Intent intent;
            switch ((int) action.getId()) {
                case (int) ACTION_RELATED:
                    setSelectedPosition(1);
                    selectedPosition = 2;
                    break;
                case (int) ACTION_PLAY:
                    selectedPosition = 0;
                    intent = new Intent(getActivity(), PlayVideoActivity.class);
                    intent.putExtra("videoId", currentMovieDetail.getMovieEpisodes().get(0).getSrc());
                    intent.putExtra("customer", (Serializable) currentMovieDetail);
                    startActivity(intent);
                    break;
                case (int) ACTION_EPISODE_LIST:
                    setSelectedPosition(0);
                    selectedPosition = 0;
                    intent = new Intent(getActivity(), EpisodeListActivity.class);
                    intent.putExtra("id", id);
                    intent.putExtra("customer", (Serializable) currentMovieDetail);
                    startActivity(intent);
                    break;
            }
        }
        //TODO: onClick Movie Card inside MovieDetailFragment
        if (item instanceof Movie) {
            Movie movie = (Movie) item;
            Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
            intent.putExtra("id", movie.getId());
            intent.putExtra("subcategoryId", movie.getSubCategories().get(0).getId());
            intent.putExtra("screenShot", movie.getScreenShot());
            getActivity().startActivity(intent);
            getActivity().finish();
        }

        //TODO: onClick Movie Card inside MovieDetailFragment
        if (item instanceof Episode) {
            Episode episode = (Episode) item;
            Intent intent = new Intent(getActivity(), PlayVideoActivity.class);
            intent.putExtra("videoId", currentMovieDetail.getMovieEpisodes().get(0).getSrc());
            intent.putExtra("customer", (Serializable) currentMovieDetail);
            startActivity(intent);

        }
    }

    //TODO: Click onItem search here
    @Override
    public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item,
                               RowPresenter.ViewHolder rowViewHolder, Row row) {
        if (mRowsAdapter.indexOf(row) > 0) {
            int backgroundColor = getResources().getColor(R.color.transparent_50);
            getView().setBackgroundColor(backgroundColor);
        } else {
            getView().setBackground(null);
        }
    }

    @Override
    public void showMovieDetail(MovieDetail movieDetail) {
        currentMovieDetail = movieDetail;
        // Setup action and detail row.
        DetailsOverviewRow detailsOverview = new DetailsOverviewRow(movieDetail);
        int width = 250;
        int height = 330;

        Glide.with(getActivity())
                .load(movieDetail.getAvatar())
                .asBitmap()
                .centerCrop()
                .error(R.drawable.bg_default_moviedetail)
                .into(new SimpleTarget<Bitmap>(width, height) {
                    @Override
                    public void onResourceReady(Bitmap resource,
                                                GlideAnimation<? super Bitmap>
                                                        glideAnimation) {
                        detailsOverview.setImageBitmap(getActivity(), resource);
                        actionAdapter = new ArrayObjectAdapter();
                        mActionPlay = new Action(ACTION_PLAY, getString(R.string.action_play));
                        mActionEpisodeList = new Action(ACTION_EPISODE_LIST, getString(R.string.action_episode_list));
                        mActionRelated = new Action(ACTION_RELATED, getString(R.string.action_relate));

                        actionAdapter.add(mActionPlay);
                        actionAdapter.add(mActionEpisodeList);
                        actionAdapter.add(mActionRelated);

                        detailsOverview.setActionsAdapter(actionAdapter);
                        mRowsAdapter.add(detailsOverview);

                        movieDetailPresent.getMovieList(subcategoryId, 0, 10);

                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);

                        Glide.with(getActivity())
                                .load(R.drawable.bg_default_moviedetail)
                                .asBitmap()
                                .centerCrop()
                                .error(R.drawable.bg_default_moviedetail).into(new SimpleTarget<Bitmap>(width, height) {
                            @Override
                            public void onResourceReady(Bitmap resource,
                                                        GlideAnimation<? super Bitmap>
                                                                glideAnimation) {
                                detailsOverview.setImageBitmap(getActivity(), resource);
                                actionAdapter = new ArrayObjectAdapter();
                                mActionPlay = new Action(ACTION_PLAY, getString(R.string.action_play));
                                mActionEpisodeList = new Action(ACTION_EPISODE_LIST, getString(R.string.action_episode_list));
                                mActionRelated = new Action(ACTION_RELATED, getString(R.string.action_relate));

                                actionAdapter.add(mActionPlay);
                                actionAdapter.add(mActionEpisodeList);
                                actionAdapter.add(mActionRelated);

                                detailsOverview.setActionsAdapter(actionAdapter);
                                mRowsAdapter.add(detailsOverview);
                                movieDetailPresent.getMovieList(subcategoryId, 0, 10);
                            }
                        });

                    }
                });


    }

    @Override
    public void showMovieList(List<Movie> movieList) {
        ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(
                new MoviePresenterSelector(getActivity()));
        for (Movie movie : movieList) listRowAdapter.add(movie);
        CustomHeaderItem customHeaderItem = new CustomHeaderItem(0, getString(R.string.action_relate));
        mRowsAdapter.add(new ListRow(customHeaderItem, listRowAdapter)); // Setup recommended row.

        movieDetailPresent.getEpisodeList(id, 0, 20);
    }

    @Override
    public void showEpisodeList(List<Episode> episodeList) {
        // Setup related row.

        ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(new EpisodePresenterSelector(getActivity()));
        for (Episode episode : episodeList) listRowAdapter.add(episode);
        CustomHeaderItem customHeaderItem = new CustomHeaderItem(1, getString(R.string.action_episode_list));
        mRowsAdapter.add(new ListRow(customHeaderItem, listRowAdapter));

        setAdapter(mRowsAdapter);
        getFragmentManager().beginTransaction().remove(mSpinnerFragment).commit();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startEntranceTransition();
            }
        }, 500);
        initializeBackground(Uri.parse(currentMovieDetail.getScreenShot()));

    }


    @Override
    public void showError() {
        getFragmentManager().beginTransaction().remove(mSpinnerFragment).commit();
    }

    @Override
    public void complete() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        glideBackgroundManager.loadImage(screenShot);
    }
}
