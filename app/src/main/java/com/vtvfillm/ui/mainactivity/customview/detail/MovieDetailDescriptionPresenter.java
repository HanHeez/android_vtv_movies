package com.vtvfillm.ui.mainactivity.customview.detail;

import android.content.Context;
import android.support.v17.leanback.widget.Presenter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vtvfillm.R;

import javax.inject.Inject;

import fr.bmartel.youtubetv.media.MovieDetail;


//Custom Movie Detail Here
public class MovieDetailDescriptionPresenter extends Presenter {
    private Context mContext;

    @Inject
    public MovieDetailDescriptionPresenter(Context context) {
        mContext = context;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.detail_view_content, null);
        return new MovieDetailViewHolder(view);
    }

    @Override public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        MovieDetail movie = (MovieDetail) item;
        MovieDetailViewHolder holder = (MovieDetailViewHolder) viewHolder;
        holder.bind(movie);
    }

    @Override public void onUnbindViewHolder(ViewHolder viewHolder) {
        // Nothing to do here.
    }
}
