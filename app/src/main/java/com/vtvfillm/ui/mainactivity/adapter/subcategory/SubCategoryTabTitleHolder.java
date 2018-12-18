package com.vtvfillm.ui.mainactivity.adapter.subcategory;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vtvfillm.R;

public class SubCategoryTabTitleHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public View lineView;

    public SubCategoryTabTitleHolder(View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.tv_title);
        lineView = itemView.findViewById(R.id.title_line_view);
    }
}
