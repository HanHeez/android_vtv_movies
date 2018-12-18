package com.vtvfillm.ui.mainactivity.adapter.episode;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vtvfillm.R;

public class EpisodeTabTitleHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public View lineView;

    public EpisodeTabTitleHolder(View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.tv_title);
        lineView = itemView.findViewById(R.id.title_line_view);
    }
}
