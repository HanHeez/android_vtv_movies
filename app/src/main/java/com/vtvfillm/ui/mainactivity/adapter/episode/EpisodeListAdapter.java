package com.vtvfillm.ui.mainactivity.adapter.episode;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.vtvfillm.R;
import com.vtvfillm.base.BaseAdapter;
import com.vtvfillm.model.Episode;
import com.vtvfillm.ui.mainactivity.activity.PlayVideoActivity;
import com.vtvfillm.ui.mainactivity.customview.episode.border.MainUpView;
import com.vtvfillm.ui.mainactivity.fragment.EpisodeListFragment;
import com.vtvfillm.utils.ImageUtils;
import com.vtvfillm.utils.ViewUtils;

import java.io.Serializable;
import java.util.List;

import fr.bmartel.youtubetv.media.MovieDetail;

public class EpisodeListAdapter extends BaseAdapter<Episode, EpisodeListFragment> {

    MainUpView mainUpView;
    private MovieDetail currentMovieDetail;
    EpisodeListFragment context;
    public EpisodeListAdapter(List<Episode> data, EpisodeListFragment context, MovieDetail movieDetail) {
        super(data, context);
        this.context = context;
        currentMovieDetail = movieDetail;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_episode_list, null);
        view.setFocusable(true);
        return new EpisodeListHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Episode episode = mData.get(position);
        final EpisodeListHolder episodeListHolder = (EpisodeListHolder) holder;
        episodeListHolder.txtEpisodeTitle.setText(episode.getName());

        ImageUtils.loadImageByGlide(mContext.getActivity(), episode.getImageUrl(), episodeListHolder.imgEpisode);
        episodeListHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                episodeListHolder.txtEpisodeTitle.setTextColor(mContext.getResources().getColor(hasFocus ? R.color.title_select_color : R.color.movie_name_card_color));
                ViewUtils.scaleView(v, hasFocus);
            }
        });

        episodeListHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent = new Intent(context.getContext(), PlayVideoActivity.class);
                intent.putExtra("videoId", currentMovieDetail.getMovieEpisodes().get(position).getSrc());
                intent.putExtra("customer", (Serializable) currentMovieDetail);
                intent.putExtra("index",position);
                mContext.startActivity(intent);
            }
        });

    }

    public void setMainView(MainUpView mainUpView) {
        this.mainUpView = mainUpView;
    }
}