package com.vtvfillm.ui.mainactivity.presenter;

import com.vtvfillm.api.MoviesApi;
import com.vtvfillm.base.RxPresenter;
import com.vtvfillm.ui.mainactivity.contract.EpisodeListContract;
import com.vtvfillm.ui.mainactivity.contract.MainContract;
import com.vtvfillm.utils.LogUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EpisodeListPresent extends RxPresenter<EpisodeListContract.View> implements EpisodeListContract.Presenter<EpisodeListContract.View> {

    private MoviesApi moviesApi;

    @Inject
    public EpisodeListPresent(MoviesApi moviesApi) {
        this.moviesApi = moviesApi;
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
}