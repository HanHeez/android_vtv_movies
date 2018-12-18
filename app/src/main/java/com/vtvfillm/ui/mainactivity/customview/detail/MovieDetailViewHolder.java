package com.vtvfillm.ui.mainactivity.customview.detail;

import android.support.v17.leanback.widget.Presenter;
import android.view.View;
import android.widget.TextView;

import com.vtvfillm.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.bmartel.youtubetv.media.MovieDetail;

public class MovieDetailViewHolder extends Presenter.ViewHolder {
    @BindView(R.id.txtMovieName)
    TextView txtMovieName;
    @BindView(R.id.txtNation)
    TextView txtNation;
    @BindView(R.id.txtEpisodeCount)
    TextView txtEpisodeCount;
    @BindView(R.id.txtRating)
    TextView txtRating;
    @BindView(R.id.txtDescription)
    TextView txtDescription;
    private View itemView;

    public MovieDetailViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        itemView = view;
    }

    public void bind(MovieDetail movie) {
        txtMovieName.setText(movie.getName());
        txtNation.setText(movie.getNation());
        txtEpisodeCount.setText(movie.getEpisodeNumber() + " tập");
        txtRating.setText(movie.getReleasedYear()+"");
        txtDescription.setText(movie.getDescription());

            // Adds each genre to the genre layout
//            for (Genre g : movie.getGenres()) {
//                TextView tv = new TextView(itemView.getContext());
//                tv.setText(g.getName());
//                GradientDrawable shape = new GradientDrawable();
//                shape.setShape(GradientDrawable.RECTANGLE);
//                shape.setCornerRadius(corner);
//                shape.setColor(ContextCompat.getColor(itemView.getContext(), R.color.primary_dark));
//                tv.setPadding(_8dp, _8dp, _8dp, _8dp);
//                tv.setBackground(shape);
//
//                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT));
//                params.setMargins(0, 0, _16dp, 0);
//                tv.setLayoutParams(params);
//
//                mGenresLayout.addView(tv);
//            }
    }
}
