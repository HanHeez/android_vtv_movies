package com.vtvfillm.ui.mainactivity.adapter.subcategory;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vtvfillm.base.BaseEpisodeFragment;
import com.vtvfillm.base.BaseMovieFragment;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryPagerAdapter extends FragmentPagerAdapter {
    private List<BaseMovieFragment> mFragments;

    public SubCategoryPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
    }

    /**
     * 添加fragment
     * @param fragment
     */
    public void add(BaseMovieFragment fragment){
        mFragments.add(fragment);
    }

    /**
     * 添加所有的Fragment
     * @param fragments
     */
    public void addAll(List<BaseMovieFragment> fragments){
        mFragments.clear();
        mFragments.addAll(fragments);
    }

    @Override
    public BaseMovieFragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

}
