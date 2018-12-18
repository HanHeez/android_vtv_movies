package com.vtvfillm.ui.mainactivity.adapter.subcategory;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vtvfillm.R;
import com.vtvfillm.base.BaseHolder;

import butterknife.BindView;

public class SubCategoryListHolder extends BaseHolder {

    @BindView(R.id.imgMovie)
    public ImageView imgMovie;
    @BindView(R.id.txtMovieName)
    public TextView txtMovieName;

    public SubCategoryListHolder(View itemView) {
        super(itemView);
        initFocus(imgMovie);
    }
}
