package com.vtvfillm.ui.mainactivity.customview.movie;

import android.content.Context;
import android.content.Intent;
import android.support.v17.leanback.widget.BaseCardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vtvfillm.R;
import com.vtvfillm.model.Movie;
import com.vtvfillm.ui.mainactivity.activity.MovieDetailActivity;
import com.vtvfillm.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieCardView extends BaseCardView {

    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.imgPoster)
    ImageView imgPoster;
    @BindView(R.id.txtRating)
    TextView txtRating;
    @BindView(R.id.txtMovieName)
    TextView txtMovieName;
    @BindView(R.id.txtDescription)
    TextView txtDescription;
    @BindView(R.id.rlMovieCard)
    RelativeLayout rlMovieCard;
    @BindView(R.id.rlTitleTagCard)
    RelativeLayout rlTitleTagCard;
    @BindView(R.id.txtTitleTag)
    TextView txtTitleTag;
    @BindView(R.id.imgLoadmore)
    ImageView imgLoadmore;
    @BindView(R.id.txtNumberEpisode)
    TextView txtNumberEpisode;

    public MovieCardView(Context context) {
        super(context, null, R.style.MovieCardStyle);
        LayoutInflater.from(getContext()).inflate(R.layout.item_card_movie, this);

        //TODO: setting color focus movie card here
        setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                RelativeLayout container = findViewById(R.id.rlTitleTagCard);
                if (hasFocus) {
                    container.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                } else {
                    container.setBackgroundColor(getResources().getColor(R.color.unselect_movie_card_background));
                }
            }
        });
        setFocusable(true);


    }


    public void updateUi(Movie movie) {
        ButterKnife.bind(this);
        if (movie.getId().equals("-1")) {
            rlMovieCard.setVisibility(GONE);
            rlTitleTagCard.setVisibility(VISIBLE);
            imgLoadmore.setVisibility(GONE);
            txtTitleTag.setVisibility(VISIBLE);
            txtTitleTag.setText(movie.getName());
        } else if (movie.getId().equals("-2")) {
            rlMovieCard.setVisibility(GONE);
            rlTitleTagCard.setVisibility(VISIBLE);
            imgLoadmore.setVisibility(VISIBLE);
            txtTitleTag.setVisibility(GONE);
        } else {
            rlMovieCard.setVisibility(VISIBLE);
            rlTitleTagCard.setVisibility(GONE);
            if (movie.getEpisodeNumber() != null && movie.getEpisodeNumber() > 1) {
                txtNumberEpisode.setText(movie.getEpisodeNumber()+"");
            } else {
                txtNumberEpisode.setVisibility(GONE);
            }
            ImageUtils.loadImageByGlide(getContext(), movie.getAvatar(), imgPoster);

            txtMovieName.setText(movie.getName());
            txtRating.setText("4.0");
            txtDescription.setText(movie.getDescription());
        }
    }
}