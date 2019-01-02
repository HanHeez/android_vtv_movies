package com.vtvfillm.ui.mainactivity.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.vtvfillm.R;
import com.vtvfillm.base.BaseAdapter;
import com.vtvfillm.base.MoviesApplication;
import com.vtvfillm.model.Episode;
import com.vtvfillm.model.HomeCategory;
import com.vtvfillm.ui.mainactivity.activity.HomeActivity;
import com.vtvfillm.ui.mainactivity.activity.PlayVideoActivity;
import com.vtvfillm.ui.mainactivity.customview.episode.border.MainUpView;
import com.vtvfillm.ui.mainactivity.fragment.EpisodeListFragment;
import com.vtvfillm.ui.mainactivity.fragment.HomeFragment;
import com.vtvfillm.utils.ImageUtils;
import com.vtvfillm.utils.ViewUtils;

import java.io.Serializable;
import java.util.List;

import fr.bmartel.youtubetv.media.MovieDetail;

public class HomeCategoryAdapter extends BaseAdapter<HomeCategory, HomeActivity> {

    MainUpView mainUpView;

    HomeActivity context;
    public HomeCategoryAdapter(List<HomeCategory> data, HomeActivity context) {
        super(data, context);
        this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_grid_home_category, null);
        view.setFocusable(true);
        return new HomeCategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        HomeCategory homeCategory = mData.get(position);
        final HomeCategoryHolder homeCategoryHolder = (HomeCategoryHolder) holder;
        homeCategoryHolder.txtCategoryName.setText(homeCategory.getName());
        homeCategoryHolder.container.setBackgroundColor(MoviesApplication.getsInstance().getResources().getColor(homeCategory.getBackground()));
        homeCategoryHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                homeCategoryHolder.txtCategoryName.setTextColor(mContext.getResources().getColor(hasFocus ? R.color.title_select_color : R.color.movie_name_card_color));
                ViewUtils.scaleView(v, hasFocus);
            }
        });

        homeCategoryHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//               Intent intent = new Intent(context.getContext(), PlayVideoActivity.class);
//                intent.putExtra("videoId", currentMovieDetail.getMovieEpisodes().get(position).getSrc());
//                intent.putExtra("customer", (Serializable) currentMovieDetail);
//                intent.putExtra("index",position);
//                mContext.startActivity(intent);
            }
        });

    }

    public void setMainView(MainUpView mainUpView) {
        this.mainUpView = mainUpView;
    }
}