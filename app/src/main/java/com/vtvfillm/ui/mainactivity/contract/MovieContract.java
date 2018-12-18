package com.vtvfillm.ui.mainactivity.contract;

import com.vtvfillm.base.BaseContract;
import com.vtvfillm.model.Category;
import com.vtvfillm.model.CategoryDetail;
import com.vtvfillm.model.Movie;

import java.util.List;

public interface MovieContract {

    interface View extends BaseContract.BaseView {
        void showSubCategoryList(List<Category> subCategoryList);
        void showSubCategoryDetail(CategoryDetail subCategoryDetail);
        void showMovieList(List<Movie> movieList, String subCategoryId, String subCategoryName, Category subCategory);
        void showMovieList(List<Movie> movieList);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getSubCategoryList(String category_id);
        void getSubCategoryDetail(String category_id);
        void getMovieList(String sub_category_id, int page, int perPage, String subCategoryName, Category subCategory);
        void searchMovieList(String q, int page, int perPage);

    }
}