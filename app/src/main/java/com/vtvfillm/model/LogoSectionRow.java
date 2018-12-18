package com.vtvfillm.model;

import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.Row;

public class LogoSectionRow extends Row {


    public LogoSectionRow(HeaderItem headerItem) {
        super(headerItem);
    }

    public LogoSectionRow(long id, String name) {
        super(new HeaderItem(id, name));
    }

    public LogoSectionRow(String name) {
        super(new HeaderItem(name));
    }

    @Override
    final public boolean isRenderedAsRowView() {
        return false;
    }
}
