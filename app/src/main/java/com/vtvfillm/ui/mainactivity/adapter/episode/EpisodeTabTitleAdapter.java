package com.vtvfillm.ui.mainactivity.adapter.episode;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.vtvfillm.R;
import com.vtvfillm.ui.mainactivity.customview.episode.tabs.AsyncFocusListener;

public class EpisodeTabTitleAdapter extends RecyclerView.Adapter {

    private int[] mData;
    private Context mContext;
    private AsyncFocusListener<Integer> mListener;

    public EpisodeTabTitleAdapter(int[] data, Context context) {
        mData = data;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_grid_title_view, null);
        view.setFocusableInTouchMode(true);
        view.setFocusable(true);
        EpisodeTabTitleHolder episodeTabTitleHolder = new EpisodeTabTitleHolder(view);
        return episodeTabTitleHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int data = mData[position];
        final EpisodeTabTitleHolder episodeTabTitleHolder = (EpisodeTabTitleHolder) holder;
        episodeTabTitleHolder.titleView.setText(mContext.getResources().getString(data));
        episodeTabTitleHolder.itemView.setTag(position);
        episodeTabTitleHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                episodeTabTitleHolder.lineView.setBackgroundColor(mContext.getResources().getColor(hasFocus ? R.color.title_select_color : R.color.clear_color));
                episodeTabTitleHolder.titleView.setTextColor(mContext.getResources().getColor(hasFocus ? R.color.title_select_color : R.color.title_none_color));
                if (mListener != null) {
                    if (hasFocus) {
                        mListener.focusPosition((Integer) v.getTag());
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    public void setAsycFocusListener(AsyncFocusListener<Integer> listener) {
        mListener = listener;
    }
}
