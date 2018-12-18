package com.vtvfillm.ui.mainactivity.presenter;

import com.vtvfillm.api.MoviesApi;
import com.vtvfillm.base.RxPresenter;
import com.vtvfillm.ui.mainactivity.contract.SubCategoryContract;
import com.vtvfillm.utils.LogUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SubCategoryPresent extends RxPresenter<SubCategoryContract.View> implements SubCategoryContract.Presenter<SubCategoryContract.View> {

    private MoviesApi moviesApi;

    @Inject
    public SubCategoryPresent(MoviesApi moviesApi) {
        this.moviesApi = moviesApi;
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


