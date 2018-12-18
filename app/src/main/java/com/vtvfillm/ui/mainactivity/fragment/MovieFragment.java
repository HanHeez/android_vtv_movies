package com.vtvfillm.ui.mainactivity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v17.leanback.app.RowsFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.PresenterSelector;
import android.util.Log;

import com.vtvfillm.R;
import com.vtvfillm.base.MoviesApplication;
import com.vtvfillm.dagger2.component.AppComponent;
import com.vtvfillm.dagger2.component.DaggerMainComponent;
import com.vtvfillm.model.Category;
import com.vtvfillm.model.CategoryDetail;
import com.vtvfillm.model.Movie;
import com.vtvfillm.model.SubCategoryListRow;
import com.vtvfillm.ui.mainactivity.activity.MovieDetailActivity;
import com.vtvfillm.ui.mainactivity.activity.SubCategoryActivity;
import com.vtvfillm.ui.mainactivity.contract.MovieContract;
import com.vtvfillm.ui.mainactivity.customview.header.CustomHeaderItem;
import com.vtvfillm.ui.mainactivity.customview.ShadowRowPresenterSelector;
import com.vtvfillm.ui.mainactivity.customview.movie.MoviePresenterSelector;
import com.vtvfillm.ui.mainactivity.presenter.MoviePresent;

import java.util.List;

import javax.inject.Inject;

public class MovieFragment extends RowsFragment implements MovieContract.View {
    private final ArrayObjectAdapter mRowsAdapter;
    List<Category> categoryList;
    String id;
    String categoryName;
    @Inject
    MoviePresent moviePresent;
    SpinnerFragment mSpinnerFragment;
    ArrayObjectAdapter adapter;
    PresenterSelector presenterSelector;

    private void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        moviePresent.attachView(this);
    }


    public MovieFragment() {
        mRowsAdapter = new ArrayObjectAdapter(new ShadowRowPresenterSelector());
        setAdapter(mRowsAdapter);
        //TODO: goto MovieDetailActivity, transfer Data here
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
                intent.putExtra("subcategoryId", movie.getSubCategoryId());
                if (movie.getScreenShot()!=null && !movie.getScreenShot().equals("")) {
                    intent.putExtra("screenShot", movie.getScreenShot());
                } else {
                    intent.putExtra("screenShot", "");
                }
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(MoviesApplication.getsInstance().getAppComponent());
        Bundle bundle = this.getArguments();
        id = bundle.getString("id");
        categoryName = bundle.getString("name");
        createRows();

        getMainFragmentAdapter().getFragmentHost().notifyDataReady(getMainFragmentAdapter());
    }

    private void createRows() {
        mSpinnerFragment = new SpinnerFragment();
        getFragmentManager().beginTransaction().add(R.id.page_list_fragment, mSpinnerFragment).commit();
        moviePresent.getSubCategoryList(id);
        presenterSelector = new MoviePresenterSelector(getActivity());
        adapter = new ArrayObjectAdapter(presenterSelector);
    }

    @Override
    public void showSubCategoryList(List<Category> subCategoryList) {
        categoryList = subCategoryList;
        mRowsAdapter.clear();
        for (int i=0;i<subCategoryList.size();i++) {
            presenterSelector = new MoviePresenterSelector(getActivity());
            adapter = new ArrayObjectAdapter(presenterSelector);
            //TODO: Setting First Item here:
            Log.d("kiemtra", subCategoryList.get(i).getName());
            adapter.add(new Movie("-1", subCategoryList.get(i).getId(), subCategoryList.get(i).getName()));

            for (Movie movie : subCategoryList.get(i).getMovies()) {
                movie.setSubCategoryId(subCategoryList.get(i).getId());
                adapter.add(movie);
            }

            adapter.add(new Movie("-2", subCategoryList.get(i).getId(), subCategoryList.get(i).getName()));

            CustomHeaderItem customHeaderItem = new CustomHeaderItem("");
            mRowsAdapter.add(new SubCategoryListRow(customHeaderItem, adapter, subCategoryList.get(i)));
            getFragmentManager().beginTransaction().remove(mSpinnerFragment).commit();
            mRowsAdapter.notifyItemRangeChanged(0, mRowsAdapter.size());
        }

    }

    @Override
    public void showMovieList(List<Movie> movieList) {

    }

    @Override
    public void showMovieList(List<Movie> movieList, String subCategoryId, String subCategoryName, Category subCategory) {

    }


    @Override
    public void showSubCategoryDetail(CategoryDetail subCategoryDetail) {

    }


    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void onDestroyView() {
        getFragmentManager().beginTransaction().remove(mSpinnerFragment).commit();
        super.onDestroyView();

    }
}

