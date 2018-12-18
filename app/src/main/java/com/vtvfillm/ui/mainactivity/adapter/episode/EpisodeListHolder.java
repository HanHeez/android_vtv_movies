package com.vtvfillm.ui.mainactivity.adapter.episode;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vtvfillm.R;
import com.vtvfillm.base.BaseHolder;

import butterknife.BindView;

public class EpisodeListHolder extends BaseHolder {

    @BindView(R.id.imgEpisode)
    public ImageView imgEpisode;
    @BindView(R.id.txtEpisodeTitle)
    public TextView txtEpisodeTitle;

    public EpisodeListHolder(View itemView) {
        super(itemView);
        initFocus(imgEpisode);
    }
}
