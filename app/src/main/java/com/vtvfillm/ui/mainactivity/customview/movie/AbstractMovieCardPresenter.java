package com.vtvfillm.ui.mainactivity.customview.movie;

import android.content.Context;

import android.support.v17.leanback.widget.BaseCardView;
import android.support.v17.leanback.widget.Presenter;
import android.view.ViewGroup;

import com.vtvfillm.model.Movie;

public abstract class AbstractMovieCardPresenter<T extends BaseCardView> extends Presenter {

    private static final String TAG = "AbstractMovieCardPresenter";
    private final Context mContext;

    /**
     * @param context The current context.
     */
    public AbstractMovieCardPresenter(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    @Override public final ViewHolder onCreateViewHolder(ViewGroup parent) {
        T cardView = onCreateView();
        return new ViewHolder(cardView);
    }
    //TODO: change Model Object of Movie here
    @Override public final void onBindViewHolder(ViewHolder viewHolder, Object item) {
        Movie movie = (Movie) item;
        onBindViewHolder(movie, (T) viewHolder.view);
    }

    @Override public final void onUnbindViewHolder(ViewHolder viewHolder) {
        onUnbindViewHolder((T) viewHolder.view);
    }

    public void onUnbindViewHolder(T cardView) {
        // Nothing to clean up. Override if necessary.
    }

    /**
     * Invoked when a new view is created.
     *
     * @return Returns the newly created view.
     */
    protected abstract T onCreateView();

    /**
     * Implement this method to update your card's view with the data bound to it.
     *
     * @param cardView The view the card is bound to.
     * @see Presenter#onBindViewHolder(Presenter.ViewHolder, Object)
     */
    public abstract void onBindViewHolder(Movie movie, T cardView);

}
