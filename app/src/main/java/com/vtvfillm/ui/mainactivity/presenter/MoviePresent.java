package com.vtvfillm.ui.mainactivity.presenter;

import com.vtvfillm.api.MoviesApi;
import com.vtvfillm.base.RxPresenter;
import com.vtvfillm.model.Category;
import com.vtvfillm.ui.mainactivity.contract.MovieContract;
import com.vtvfillm.utils.LogUtils;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MoviePresent extends RxPresenter<MovieContract.View> implements MovieContract.Presenter<MovieContract.View> {

    private MoviesApi moviesApi;

    @Inject
    public MoviePresent(MoviesApi moviesApi) {
        this.moviesApi = moviesApi;
    }

//    public MoviePresent(MovieContract.View view) {
//        attachView(view);
//    }

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

    @Override
    public void getSubCategoryDetail(String category_id) {
        Disposable refreshDispo = moviesApi
                .getSubCategoryDetail(category_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (beans) -> {
                            mView.showSubCategoryDetail(beans);
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
    public void searchMovieList(String q, int page, int perPage) {
        Disposable refreshDispo = moviesApi
                .searchMovieList(q, page, perPage)
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

    @Override
    public void getMovieList(String sub_category_id, int page, int perPage, String subCategoryName, Category subCategory) {
        Disposable refreshDispo = moviesApi
                .getMovieList(sub_category_id, page, perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (beans) -> {
                            mView.showMovieList(beans, sub_category_id, subCategoryName, subCategory);
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

