package com.vtvfillm.ui.mainactivity.adapter.episode;



import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.vtvfillm.base.BaseEpisodeFragment;

import java.util.ArrayList;
import java.util.List;

public class EpisodePagerAdapter extends FragmentPagerAdapter {
    private List<BaseEpisodeFragment> mFragments;

    public EpisodePagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
    }

    /**
     * 添加fragment
     * @param fragment
     */
    public void add(BaseEpisodeFragment fragment){
        mFragments.add(fragment);
    }

    /**
     * 添加所有的Fragment
     * @param fragments
     */
    public void addAll(List<BaseEpisodeFragment> fragments){
        mFragments.clear();
        mFragments.addAll(fragments);
    }

    @Override
    public BaseEpisodeFragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

}
