package fr.bmartel.youtubetv.media;

import android.support.v17.leanback.widget.AbstractDetailsDescriptionPresenter;

import fr.bmartel.youtubetv.model.Episode;

public class DescriptionPresenter extends AbstractDetailsDescriptionPresenter {

    Episode movie = new Episode();
    private static final String TAG = DescriptionPresenter.class.getSimpleName();

    public DescriptionPresenter (Episode movie){
        this.movie = movie;
    }

    @Override
    protected void onBindDescription(AbstractDetailsDescriptionPresenter.ViewHolder viewHolder, Object item) {
//        viewHolder.getTitle().setText(((Movie) item).getName());
//        viewHolder.getSubtitle().setText(((Movie) item).getDescription());

        viewHolder.getTitle().setText(movie.getName());
        viewHolder.getSubtitle().setText(movie.getShortDescription());

    }
}