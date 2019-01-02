package com.vtvfillm.ui.mainactivity.activity;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;

import com.open.leanback.widget.HorizontalGridView;
import com.vtvfillm.R;
import com.vtvfillm.ui.mainactivity.customview.episode.tabs.AsyncFocusListener;
import com.vtvfillm.ui.mainactivity.adapter.episode.EpisodePagerAdapter;
import com.vtvfillm.ui.mainactivity.adapter.episode.EpisodeTabTitleAdapter;
import com.vtvfillm.ui.mainactivity.customview.episode.tabs.TvViewPager;
import com.vtvfillm.base.BaseEpisodeFragment;
import com.vtvfillm.ui.mainactivity.fragment.EpisodeListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.bmartel.youtubetv.media.MovieDetail;

public class EpisodeListActivity extends FragmentActivity {
    private String id;
    Unbinder unbinder;
    private List<BaseEpisodeFragment> fragmentList;
    @BindView(R.id.viewPager)
    TvViewPager viewPager;
    @BindView(R.id.tabsTitle)
    HorizontalGridView tabsTitle;
    private int mPosition = -1;
    MovieDetail movieDetail;


    private int[] mainTabs = new int[]{
            R.string.episode_list_tab_home
//            R.string.episode_list_tab_recommend,
//            R.string.episode_list_tab_seen
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_list);
        unbinder = ButterKnife.bind(this);
        id = getIntent().getStringExtra("id");
        movieDetail = (MovieDetail)getIntent().getExtras().getSerializable("customer");

        initData();
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        EpisodePagerAdapter pageAdapter = new EpisodePagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < mainTabs.length; i++) {
            if (i == 0) {
                BaseEpisodeFragment fragment = new EpisodeListFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putSerializable("customer", movieDetail);

                fragment.setArguments(bundle);
                fragmentList.add(fragment);
                pageAdapter.add(fragment);

            }
//            else if (i == 1) {
//                Fragment fragment = new EpisodeRecommendFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("id", id);
//                fragment.setArguments(bundle);
//                fragmentList.add(fragment);
//                pageAdapter.add(fragment);
//
//            } else if (i == 2) {
//                Fragment fragment = new EpisodeListFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("id", id);
//                fragment.setArguments(bundle);
//                fragmentList.add(fragment);
//                pageAdapter.add(fragment);
//            }
        }

        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(pageAdapter);


        EpisodeTabTitleAdapter episodeTabTitleAdapter = new EpisodeTabTitleAdapter(mainTabs, this);
        episodeTabTitleAdapter.setAsycFocusListener(new AsyncFocusListener<Integer>() {
            @Override
            public void focusPosition(Integer position) {


                int currentItem = viewPager.getCurrentItem();
                if (position == currentItem) {
                    //回到首页
                    Fragment baseFragment = fragmentList.get(currentItem);
                } else {
                    mPosition = position;
                }
            }
        });

        tabsTitle.setHorizontalMargin(getResources().getDimensionPixelOffset(R.dimen.h_40));
        tabsTitle.setAdapter(episodeTabTitleAdapter);
        tabsTitle.setSelectedPosition(0);
        mPosition = 0;

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                View focusedChild = tabsTitle.getFocusedChild();
                if (focusedChild == null) {
                    tabsTitle.setSelectedPosition(mPosition);
                    tabsTitle.requestFocusFromTouch();
                } else if (focusedChild != null && mPosition != 0) {
                    tabsTitle.setSelectedPosition(0);
                    tabsTitle.requestFocusFromTouch();
                } else {
                    finish();
                }
                return false;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}


