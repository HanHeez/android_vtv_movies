package com.vtvfillm.base;

import android.view.View;

public interface OnEpisodeItemClickListener<T> {

    void onEpisodeItemClick(View view, T item, int adapterPosition);
}