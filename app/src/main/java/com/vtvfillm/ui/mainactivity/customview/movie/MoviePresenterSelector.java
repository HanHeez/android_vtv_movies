package com.vtvfillm.ui.mainactivity.customview.movie;

import android.content.Context;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;

import com.vtvfillm.model.Movie;

public class MoviePresenterSelector extends PresenterSelector {

    private final Context mContext;
    public MoviePresenterSelector(Context context) {
        mContext = context;
    }

    @Override
    public Presenter getPresenter(Object item) {
        if (!(item instanceof Movie)) throw new RuntimeException(
                String.format("The PresenterSelector only supports data items of type '%s'",
                        Movie.class.getName()));
        Movie movie = (Movie) item;
        Presenter presenter = new MovieCardPresenter(mContext);
        return presenter;
    }

}
