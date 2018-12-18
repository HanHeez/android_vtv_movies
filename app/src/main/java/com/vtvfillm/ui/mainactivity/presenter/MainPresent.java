package com.vtvfillm.ui.mainactivity.presenter;

import com.vtvfillm.api.MoviesApi;
import com.vtvfillm.base.RxPresenter;
import com.vtvfillm.ui.mainactivity.contract.MainContract;
import com.vtvfillm.utils.LogUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresent extends RxPresenter<MainContract.View> implements MainContract.Presenter<MainContract.View> {

    private MoviesApi moviesApi;

    @Inject
    public MainPresent(MoviesApi moviesApi) {
        this.moviesApi = moviesApi;
    }

    @Override
    public void getCategoryList() {
        Disposable refreshDispo = moviesApi
                .getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (beans) -> {
                            mView.showCategoryList(beans);
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
    public void getSubCategoryList(String category_id) {
        Disposable refreshDispo = moviesApi
                .getSubCategoryList(category_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (beans) -> {
                            mView.showSubCategoryList(beans);
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