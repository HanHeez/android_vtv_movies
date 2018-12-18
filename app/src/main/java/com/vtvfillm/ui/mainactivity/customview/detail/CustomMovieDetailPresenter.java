package com.vtvfillm.ui.mainactivity.customview.detail;

import android.support.v17.leanback.widget.DetailsOverviewLogoPresenter;
import android.support.v17.leanback.widget.FullWidthDetailsOverviewRowPresenter;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.RowPresenter;
import android.view.View;
import android.view.ViewGroup;

import com.vtvfillm.R;

public class CustomMovieDetailPresenter extends FullWidthDetailsOverviewRowPresenter {
    private int mPreviousState = STATE_FULL;

    public CustomMovieDetailPresenter(Presenter detailsPresenter, DetailsOverviewLogoPresenter logoPresenter) {
        super(detailsPresenter, logoPresenter);
        setInitialState(FullWidthDetailsOverviewRowPresenter.STATE_FULL);
    }
    //TODO: Custom Logo Movie Details here
    @Override
    protected void onLayoutLogo(ViewHolder viewHolder, int oldState, boolean logoChanged) {
        super.onLayoutLogo(viewHolder, oldState, logoChanged);
        View v = viewHolder.getLogoViewHolder().view;
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();

        lp.setMarginStart(v.getResources().getDimensionPixelSize(
                R.dimen.lb_details_v2_logo_margin_start));
        lp.topMargin = v.getResources().getDimensionPixelSize(R.dimen.lb_details_v2_blank_height) - lp.height / 2;

        float offset = v.getResources().getDimensionPixelSize(R.dimen.lb_details_v2_actions_height) + v
                .getResources().getDimensionPixelSize(R.dimen.lb_details_v2_description_margin_top) + (lp.height / 2);

        switch (viewHolder.getState()) {
            case STATE_FULL:
            default:
                if (mPreviousState == STATE_HALF) {
                    v.animate().setDuration(50).translationYBy(-offset);
                }

                break;
            case STATE_HALF:
                if (mPreviousState == STATE_FULL) {
                    v.animate().setDuration(50).translationYBy(offset);
                }

                break;
        }
        mPreviousState = viewHolder.getState();
        v.setLayoutParams(lp);
    }

    @Override
    protected void onBindRowViewHolder(RowPresenter.ViewHolder holder, Object item) {
        super.onBindRowViewHolder(holder, item);

        FullWidthDetailsOverviewRowPresenter.ViewHolder vh = (FullWidthDetailsOverviewRowPresenter.ViewHolder) holder;
        View v = vh.getOverviewView();
        v.setBackgroundColor(getBackgroundColor());
        v.findViewById(R.id.details_overview_actions_background)
                .setBackgroundColor(getActionsBackgroundColor());
        v.findViewById(R.id.details_frame)
                .setBackgroundColor(getBackgroundColor());
    }
}
