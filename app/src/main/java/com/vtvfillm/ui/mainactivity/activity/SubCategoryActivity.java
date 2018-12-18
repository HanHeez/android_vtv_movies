package com.vtvfillm.ui.mainactivity.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.open.leanback.widget.HorizontalGridView;
import com.vtvfillm.R;
import com.vtvfillm.base.BaseMovieFragment;
import com.vtvfillm.ui.mainactivity.customview.episode.tabs.AsyncFocusListener;
import com.vtvfillm.ui.mainactivity.adapter.episode.EpisodeTabTitleAdapter;
import com.vtvfillm.ui.mainactivity.customview.episode.tabs.TvViewPager;
import com.vtvfillm.ui.mainactivity.adapter.subcategory.SubCategoryPagerAdapter;
import com.vtvfillm.ui.mainactivity.fragment.SubCategoryFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SubCategoryActivity extends FragmentActivity {
    private String id;
    private String categoryName;
    Unbinder unbinder;
    private List<BaseMovieFragment> fragmentList;
    @BindView(R.id.viewPager)
    TvViewPager viewPager;
    @BindView(R.id.tabsTitle)
    HorizontalGridView tabsTitle;
    @BindView(R.id.txtCategory)
    TextView txtCategory;
    private int mPosition = -1;



    private int[] mainTabs = new int[]{
            R.string.movie_list_tab_home
//            R.string.episode_list_tab_recommend,
//            R.string.episode_list_tab_seen
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category_movie);
        unbinder = ButterKnife.bind(this);
        id = getIntent().getStringExtra("id");
        categoryName = getIntent().getStringExtra("categoryName");
        txtCategory.setText(categoryName);
        initData();
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        SubCategoryPagerAdapter pageAdapter = new SubCategoryPagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < mainTabs.length; i++) {
            if (i == 0) {
                BaseMovieFragment fragment = new SubCategoryFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
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
                //处理当前焦点的位置

                int currentItem = viewPager.getCurrentItem();
                if (position == currentItem) {
                    //回到首页
                    Fragment baseFragment = fragmentList.get(currentItem);
                } else {
                    mPosition = position;
                }
            }
        });

        tabsTitle.setHorizontalMargin(getResources().getDimensionPixelOffset(R.dimen.w_40));
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


