package com.vtvfillm.ui.mainactivity.contract;

import com.vtvfillm.base.BaseContract;
import com.vtvfillm.model.Episode;
import com.vtvfillm.model.Movie;

import java.util.List;

import fr.bmartel.youtubetv.media.MovieDetail;

public interface MovieDetailContract {

    interface View extends BaseContract.BaseView {
        void showMovieDetail(MovieDetail movieDetail);
        void showEpisodeList(List<Episode> episodeList);
        void showMovieList(List<Movie> movieList);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getMovieDetail(String movieId);
        void getEpisodeList(String movieId, int page, int perPage);
        void getMovieList(String sub_category_id, int page, int perPage);
    }
}