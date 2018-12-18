package com.vtvfillm.ui.mainactivity.adapter.subcategory;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.vtvfillm.R;
import com.vtvfillm.base.BaseAdapter;
import com.vtvfillm.model.Movie;
import com.vtvfillm.ui.mainactivity.activity.MovieDetailActivity;
import com.vtvfillm.ui.mainactivity.customview.episode.border.MainUpView;
import com.vtvfillm.ui.mainactivity.fragment.SubCategoryFragment;
import com.vtvfillm.utils.ImageUtils;
import com.vtvfillm.utils.ViewUtils;

import java.util.List;

import javax.inject.Inject;

public class SubCategoryListAdapter extends BaseAdapter<Movie, SubCategoryFragment> {

    MainUpView mainUpView;

    public SubCategoryListAdapter(List<Movie> data, SubCategoryFragment context) {
        super(data, context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_sub_category_list, null);
        view.setFocusable(true);
        return new SubCategoryListHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Movie movie = mData.get(position);
        final SubCategoryListHolder subCategoryListHolder = (SubCategoryListHolder) holder;
        subCategoryListHolder.txtMovieName.setText(movie.getName());

        ImageUtils.loadImageByGlide(mContext.getActivity(), movie.getAvatar(), subCategoryListHolder.imgMovie);

        subCategoryListHolder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                subCategoryListHolder.txtMovieName.setTextColor(mContext.getResources().getColor(hasFocus ? R.color.title_select_color : R.color.movie_name_card_color));
                ViewUtils.scaleView(v, hasFocus);
            }
        });
        //TODO: OnClick "Subcategory" movie, set random id subcategories here
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext.getActivity(),  MovieDetailActivity.class);
                intent.putExtra("id", movie.getId());
                intent.putExtra("subcategoryId", movie.getSubCategories().get(0).getId());
                if (movie.getScreenShot()!=null) {
                    intent.putExtra("screenShot", movie.getScreenShot());
                } else {
                    intent.putExtra("screenShot", "");
                }
                mContext.getActivity().startActivity(intent);
            }
        });
    }

    public void setMainView(MainUpView mainUpView) {
        this.mainUpView = mainUpView;
    }
}
