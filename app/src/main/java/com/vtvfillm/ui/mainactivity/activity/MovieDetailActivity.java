package com.vtvfillm.ui.mainactivity.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v17.leanback.widget.BrowseFrameLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.vtvfillm.R;
import com.vtvfillm.ui.mainactivity.fragment.MovieDetailFragment;

public class MovieDetailActivity extends Activity {
    private String id;
    private String screenShot = "";
    private String subcategoryId;
    MovieDetailFragment fragment;
    BrowseFrameLayout mRootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        id = getIntent().getStringExtra("id");
        subcategoryId = getIntent().getStringExtra("subcategoryId");
        screenShot = getIntent().getStringExtra("screenShot");
        if (savedInstanceState == null) {
            fragment = new MovieDetailFragment();
            Bundle args = new Bundle();
            args.putString("id", id);
            args.putString("subcategoryId", subcategoryId);
            args.putString("screenShot", screenShot);
            fragment.setArguments(args);
            getFragmentManager().beginTransaction()
                    .replace(R.id.details_fragment, fragment)
                    .commit();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                fragment.changePositionWhenPressDown();
            }

            if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                fragment.changePositionWhenPressUp();

            }
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                fragment.changePositionWhenPressBack();
            }

        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void onBackPressed() {

    }
}
