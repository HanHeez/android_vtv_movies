package com.vtvfillm.model;

import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ObjectAdapter;

import com.vtvfillm.ui.mainactivity.customview.header.CustomHeaderItem;

public class SubCategoryListRow extends ListRow {

    private Category category;

    public SubCategoryListRow(CustomHeaderItem header, ObjectAdapter adapter, Category category) {
        super(header, adapter);
        setCategory(category);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
