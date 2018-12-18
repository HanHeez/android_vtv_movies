package com.vtvfillm.ui.mainactivity.customview.header;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.PageRow;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.RowHeaderPresenter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vtvfillm.R;
import com.vtvfillm.base.MoviesApplication;

public class CustomHeaderItemPresenter extends RowHeaderPresenter {
    private static final String TAG = CustomHeaderItemPresenter.class.getSimpleName();

    private float mUnselectedAlpha;
    View view;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        mUnselectedAlpha = viewGroup.getResources()
                .getFraction(R.fraction.lb_browse_header_unselect_alpha, 1, 1);
        LayoutInflater inflater = (LayoutInflater) viewGroup.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.custom_main_header_item, null);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object o) {
            //TODO: Custom Header here
            CustomHeaderItem iconHeaderItem = (CustomHeaderItem) ((PageRow) o).getHeaderItem();
            View rootView = viewHolder.view;

            ImageView iconView = rootView.findViewById(R.id.header_icon);
            int iconResId = iconHeaderItem.getIconResId();

            if (iconResId != CustomHeaderItem.ICON_NONE) { // Show icon only when it is set.
                Drawable icon = rootView.getResources().getDrawable(iconResId, null);
                iconView.setImageDrawable(icon);
            }

            TextView label = rootView.findViewById(R.id.header_label);
            label.setText(iconHeaderItem.getName());


    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        // no op
    }

    // also assumes the xml inflation will return a RowHeaderView
    @Override
    protected void onSelectLevelChanged(RowHeaderPresenter.ViewHolder holder) {
        // this is a temporary fix
        View rootView = holder.view;
        ImageView iconView = rootView.findViewById(R.id.header_icon);
        TextView txtHeader = rootView.findViewById(R.id.header_label);
        //TODO: custom color Header text and icon here
        if (holder.getSelectLevel()==0) {
            txtHeader.setTextColor(MoviesApplication.getsInstance().getResources().getColor(R.color.white));
            iconView.setImageDrawable(MoviesApplication.getsInstance().getResources().getDrawable(R.drawable.ic_forward));
        } else {
            txtHeader.setTextColor(MoviesApplication.getsInstance().getResources().getColor(R.color.red));
            iconView.setImageDrawable(MoviesApplication.getsInstance().getResources().getDrawable(R.drawable.icon_forward_red));
        }
        holder.view.setAlpha(mUnselectedAlpha + holder.getSelectLevel() *
                (1.0f - mUnselectedAlpha));


    }

}
