package com.vtvfillm.ui.mainactivity.customview.episode;

import android.content.Context;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;

import com.vtvfillm.model.Episode;
import com.vtvfillm.model.Movie;
import com.vtvfillm.ui.mainactivity.customview.movie.MovieCardPresenter;

public class EpisodePresenterSelector extends PresenterSelector {

    private final Context mContext;
    public EpisodePresenterSelector(Context context) {
        mContext = context;
    }

    @Override
    public Presenter getPresenter(Object item) {
        if (!(item instanceof Episode)) throw new RuntimeException(
                String.format("The PresenterSelector only supports data items of type '%s'",
                        Movie.class.getName()));
        Episode episode = (Episode) item;
        Presenter presenter = new EpisodeCardPresenter(mContext);
        return presenter;
    }

}
