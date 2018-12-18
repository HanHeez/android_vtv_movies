package com.vtvfillm.ui.mainactivity.customview.episode;

import android.content.Context;
import android.support.v17.leanback.widget.BaseCardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vtvfillm.R;
import com.vtvfillm.model.Episode;
import com.vtvfillm.model.Movie;
import com.vtvfillm.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EpisodeCardView extends BaseCardView {

    @BindView(R.id.imgPoster)
    ImageView imgPoster;
    @BindView(R.id.txtRating)
    TextView txtRating;
    @BindView(R.id.txtMovieName)
    TextView txtMovieName;
    @BindView(R.id.txtDescription)
    TextView txtDescription;
    @BindView(R.id.rlEpisodeCard)
    RelativeLayout rlEpisodeCard;
    @BindView(R.id.rlTitleTagCard)
    RelativeLayout rlTitleTagCard;   

    public EpisodeCardView(Context context) {
        super(context, null, R.style.MovieCardStyle);
        LayoutInflater.from(getContext()).inflate(R.layout.item_episode_card, this);

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

    public void updateUi(Episode episode) {
        ButterKnife.bind(this);        
            rlEpisodeCard.setVisibility(VISIBLE);
            rlTitleTagCard.setVisibility(GONE);

            ImageUtils.loadImageByGlide(getContext(), episode.getImageUrl(), imgPoster);

            txtMovieName.setText(episode.getName());
            txtRating.setText("4.0");
            txtDescription.setText(episode.getDescription());

        
    }
}