package com.vtvfillm.ui.mainactivity.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.ObjectAdapter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.util.Log;

import com.vtvfillm.base.MoviesApplication;
import com.vtvfillm.dagger2.component.AppComponent;
import com.vtvfillm.dagger2.component.DaggerMainComponent;
import com.vtvfillm.model.Category;
import com.vtvfillm.model.CategoryDetail;
import com.vtvfillm.model.Movie;
import com.vtvfillm.ui.mainactivity.activity.MovieDetailActivity;
import com.vtvfillm.ui.mainactivity.activity.SubCategoryActivity;
import com.vtvfillm.ui.mainactivity.contract.MovieContract;
import com.vtvfillm.ui.mainactivity.customview.movie.MovieCardPresenter;
import com.vtvfillm.ui.mainactivity.presenter.MoviePresent;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class SearchFragment extends android.support.v17.leanback.app.SearchFragment implements android.support.v17.leanback.app.SearchFragment.SearchResultProvider , MovieContract.View{

    private static final String TAG = SearchFragment.class.getSimpleName();

    ArrayList<Movie> getmItems = new ArrayList<>();
    @Inject
    MoviePresent moviePresent;

    private ArrayObjectAdapter mRowsAdapter;
    private ArrayList<Movie> mItems = getmItems;

    private String mQuery;

    private void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        moviePresent.attachView(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(MoviesApplication.getsInstance().getAppComponent());
        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        setSearchResultProvider(this);
        setTitle("Tìm kiếm");

        setOnItemViewClickedListener((OnItemViewClickedListener) (itemViewHolder, item, rowViewHolder, row) -> {
            Movie movie = (Movie) item;
            if (movie.getId().equals("-1") || movie.getId().equals("-2")) {
                Intent intent = new Intent(getActivity(), SubCategoryActivity.class);
                intent.putExtra("id", movie.getSubCategoryId());
                intent.putExtra("categoryName", movie.getName());
                startActivity(intent);
            } else {
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra("id", movie.getId());
                intent.putExtra("subcategoryId", movie.getSubCategories().get(0).getId());
                intent.putExtra("screenShot", movie.getScreenShot());
                startActivity(intent);
            }
        });
    }

@Override
public ObjectAdapter getResultsAdapter() {
    Log.d(TAG, "getResultsAdapter");
    // Delete previously implemented mock code.
    // mRowsAdapter (Search result) is already prepared in loadRows method
    return mRowsAdapter;
}

    @Override
    public boolean onQueryTextChange(String newQuery){
        Log.i(TAG, String.format("Search Query Text Change %s", newQuery));
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.i(TAG, String.format("Search Query Text Submit %s", query));
        mQuery = query;

        moviePresent.searchMovieList(query,0,30);
        return true;
    }

    @Override
    public void showSubCategoryList(List<Category> subCategoryList) {

    }

    @Override
    public void showSubCategoryDetail(CategoryDetail subCategoryDetail) {

    }

    @Override
    public void showMovieList(List<Movie> movieList) {
        getmItems.clear();
        getmItems.addAll(movieList);
        loadRows();
    }

    @Override
    public void showMovieList(List<Movie> movieList, String subCategoryId, String subCategoryName, Category subCategory) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    private void loadRows() {
        // offload processing from the UI thread
        new AsyncTask<String, Void, ListRow>() {
            private final String query = mQuery;

            @Override
            protected void onPreExecute() {
                mRowsAdapter.clear();
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            protected ListRow doInBackground(String... params) {
                final List<Movie> result = new ArrayList<>();
                for (Movie movie : mItems) {
                    // Main logic of search is here.
                    // Just check that "query" is contained in Title or Description or not. (NOTE: excluded studio information here)
                    if (movie.getName().toLowerCase(Locale.ENGLISH)
                            .contains(query.toLowerCase(Locale.ENGLISH))
                            || movie.getDescription().toLowerCase(Locale.ENGLISH)
                            .contains(query.toLowerCase(Locale.ENGLISH))) {
                        result.add(movie);
                    }
                }

                ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(new MovieCardPresenter(getContext()));
                listRowAdapter.addAll(0, result);
                HeaderItem header = new HeaderItem("Kết quả tìm kiếm");
                return new ListRow(header, listRowAdapter);
            }

            @Override
            protected void onPostExecute(ListRow listRow)
            {
                mRowsAdapter.add(listRow);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

}
