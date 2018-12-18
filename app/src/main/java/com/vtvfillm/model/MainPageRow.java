package com.vtvfillm.model;

import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.PageRow;
import android.support.v17.leanback.widget.Row;

public class MainPageRow extends Row {
    int position;

    public MainPageRow(HeaderItem headerItem) {
        super(headerItem);
    }

    public MainPageRow(HeaderItem headerItem, int position) {
        super(headerItem);
        this.position = position;

    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    final public boolean isRenderedAsRowView() {
        return false;
    }
}
