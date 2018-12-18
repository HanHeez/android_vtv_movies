package com.vtvfillm.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vtvfillm.utils.ViewUtils;

import butterknife.ButterKnife;

public class BaseHolder extends RecyclerView.ViewHolder {

    public BaseHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void initFocus(View view) {
        ViewUtils.onFocus(view);
    }
}
