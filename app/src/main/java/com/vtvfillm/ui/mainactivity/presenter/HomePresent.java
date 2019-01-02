package com.vtvfillm.ui.mainactivity.presenter;

import com.vtvfillm.api.MoviesApi;
import com.vtvfillm.base.RxPresenter;
import com.vtvfillm.ui.mainactivity.contract.EpisodeListContract;
import com.vtvfillm.ui.mainactivity.contract.HomeContract;
import com.vtvfillm.utils.LogUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresent extends RxPresenter<HomeContract.View> implements HomeContract.Presenter<HomeContract.View> {

    private MoviesApi moviesApi;

    @Inject
    public HomePresent(MoviesApi moviesApi) {
        this.moviesApi = moviesApi;
    }


}