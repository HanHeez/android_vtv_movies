package com.vtvfillm.ui.mainactivity.adapter.home;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vtvfillm.R;
import com.vtvfillm.base.BaseHolder;

import butterknife.BindView;

public class HomeCategoryHolder extends BaseHolder {

    @BindView(R.id.txtCategoryName)
    TextView txtCategoryName;   
    @BindView(R.id.container)
    FrameLayout container;

    public HomeCategoryHolder(View itemView) {
        super(itemView);
        initFocus(txtCategoryName);
    }
}
