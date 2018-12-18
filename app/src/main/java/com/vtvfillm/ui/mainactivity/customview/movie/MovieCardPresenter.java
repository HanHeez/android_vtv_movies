package com.vtvfillm.ui.mainactivity.customview.movie;

import android.content.Context;
import android.media.Image;
import android.support.v17.leanback.widget.ImageCardView;
import android.view.ContextThemeWrapper;

import com.bumptech.glide.Glide;
import com.vtvfillm.R;
import com.vtvfillm.base.OnCardViewItemClickListener;
import com.vtvfillm.model.Movie;

public class MovieCardPresenter extends AbstractMovieCardPresenter<MovieCardView> {
    private OnCardViewItemClickListener onCardViewItemClickListener;

    public MovieCardPresenter(Context context, int cardThemeResId) {
        super(new ContextThemeWrapper(context, cardThemeResId));
    }

    public MovieCardPresenter(Context context) {
        this(context, R.style.MovieCardStyle);
    }

    @Override
    protected MovieCardView onCreateView() {
        MovieCardView movieCardView = new MovieCardView(getContext());
        return movieCardView;
    }

    //TODO: Custom design movie card here ( extends ImageCardView )
    @Override
    public void onBindViewHolder(Movie movie, final MovieCardView cardView) {
      cardView.updateUi(movie);
    }
}


