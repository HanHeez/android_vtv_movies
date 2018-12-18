package com.vtvfillm.ui.mainactivity.contract;

import com.vtvfillm.base.BaseContract;
import com.vtvfillm.model.Movie;

import java.util.List;

public interface SubCategoryContract {
    interface View extends BaseContract.BaseView {
        void showMovieList(List<Movie> movieList);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getMovieList(String sub_category_id, int page, int perPage);
    }
}
