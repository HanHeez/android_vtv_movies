package com.vtvfillm.base;

import android.view.View;

public interface OnCardViewItemClickListener<T> {

    void onCardViewItemClick(View view, T item, int adapterPosition);
}
