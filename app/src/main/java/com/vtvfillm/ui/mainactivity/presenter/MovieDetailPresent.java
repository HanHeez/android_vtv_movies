package com.vtvfillm.ui.mainactivity.presenter;

import com.vtvfillm.api.MoviesApi;
import com.vtvfillm.base.RxPresenter;
import com.vtvfillm.model.Category;
import com.vtvfillm.ui.mainactivity.contract.MovieContract;
import com.vtvfillm.ui.mainactivity.contract.MovieDetailContract;
import com.vtvfillm.utils.LogUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailPresent extends RxPresenter<MovieDetailContract.View> implements MovieDetailContract.Presenter<MovieDetailContract.View> {
    private MoviesApi moviesApi;

    @Inject
    public MovieDetailPresent(MoviesApi moviesApi) {
        this.moviesApi = moviesApi;
    }

    @Override
    public void getMovieDetail(String movieId) {
        Disposable refreshDispo = moviesApi
                .getMovieDetail(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (beans) -> {
                            mView.showMovieDetail(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                            LogUtils.e(e);
                        }
                );
        addSubscribe(refreshDispo);
    }

    @Override
    public void getEpisodeList(String movieId, int page, int perPage) {
        Disposable refreshDispo = moviesApi
                .getEpisodeList(movieId, page, perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (beans) -> {
                            mView.showEpisodeList(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                            LogUtils.e(e);
                        }
                );
        addSubscribe(refreshDispo);
    }

    @Override
    public void getMovieList(String sub_category_id, int page, int perPage) {
        Disposable refreshDispo = moviesApi
                .getMovieList(sub_category_id, page, perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (beans) -> {
                            mView.showMovieList(beans);
                        }
                        ,
                        (e) -> {
                            mView.showError();
                            LogUtils.e(e);
                        }
                );
        addSubscribe(refreshDispo);
    }
}

