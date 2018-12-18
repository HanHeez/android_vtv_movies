package com.vtvfillm.api;

import com.vtvfillm.model.Category;
import com.vtvfillm.model.CategoryDetail;
import com.vtvfillm.model.Episode;
import com.vtvfillm.model.Movie;

import java.util.List;

import fr.bmartel.youtubetv.media.MovieDetail;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesService {

    @GET("v1/categories/")
    Observable<List<Category>> getCategoryList(@Header("Authorization") String token);

    @GET("v1/categories/app")
    Observable<List<Category>> getCategoryList();

    @GET("v1/categories/app/{category_id}/sub_categories")
    Observable<List<Category>> getSubCategoryList(@Path("category_id") String category_id);

    @GET("v1/categories/app/{category_id}")
    Observable<CategoryDetail> getSubCategoryDetail(@Path("category_id") String category_id);

    @GET("v1/movies/app")
    Observable<List<Movie>> getMovieList(@Query("sub_category_id") String sub_category_id, @Query("page") int page, @Query("perPage") int perPage);

    @GET("v1/movies/app/{movieId}")
    Observable<MovieDetail> getMovieDetail(@Path("movieId") String movieId);

    @GET("v1/movies/app/{movieId}/episodes")
    Observable<List<Episode>> getEpisodeList(@Path("movieId") String movieId, @Query("page") int page, @Query("perPage") int perPage);

    @GET("v1/movies/app/search")
    Observable<List<Movie>> searchMovieList(@Query("q") String q, @Query("page") int page, @Query("perPage") int perPage);


}
