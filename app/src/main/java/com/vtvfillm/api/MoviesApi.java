package com.vtvfillm.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vtvfillm.model.Category;
import com.vtvfillm.model.CategoryDetail;
import com.vtvfillm.model.Episode;
import com.vtvfillm.model.Movie;

import java.util.List;

import fr.bmartel.youtubetv.media.MovieDetail;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class MoviesApi {

    public static MoviesApi instance;

    private MoviesService service;

    public MoviesApi(OkHttpClient okHttpClient) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_ENDPOINT)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(MoviesService.class);
    }

    public static MoviesApi getInstance(OkHttpClient okHttpClient) {
        if (instance == null)
            instance = new MoviesApi(okHttpClient);
        return instance;
    }

    // Setting Api Here
    public Observable<List<Category>> getCategory(String token) {
        return service.getCategoryList(token);
    }

    public Observable<List<Category>> getCategory() {
        return service.getCategoryList();
    }

    public Observable<List<Category>> getSubCategoryList(String category_id) {
        return service.getSubCategoryList(category_id);
    }

    public Observable<CategoryDetail> getSubCategoryDetail(String category_id) {
        return service.getSubCategoryDetail(category_id);
    }

    public Observable<List<Movie>> getMovieList(String sub_category_id, int page, int perPage){
        return service.getMovieList(sub_category_id,page,perPage);
    }

    public Observable<List<Movie>> searchMovieList(String q, int page, int perPage){
        return service.searchMovieList(q,page,perPage);
    }

    public Observable<MovieDetail> getMovieDetail(String movieId) {
        return service.getMovieDetail(movieId);
    }

    public Observable<List<Episode>> getEpisodeList(String movieId, int page, int perPage) {
        return service.getEpisodeList(movieId, page, perPage);
    }

}
