package com.vtvfillm.factory;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.vtvfillm.R;
import com.vtvfillm.base.MoviesApplication;

import java.util.Timer;
import java.util.TimerTask;

public class GlideBackgroundManager {

    private static final String TAG = GlideBackgroundManager.class.getSimpleName();

    private static final int BACKGROUND_UPDATE_DELAY = 300;
    private BackgroundManager mBackgroundManager;
    private Drawable mDefaultBackground;
    private DisplayMetrics mMetrics;
    private Timer mBackgroundTimer;
    private Handler mHandler = new Handler();
    private String mBackgroundUri;
    private Activity activity;

    public static GlideBackgroundManager instance;

    /**
     * @param activity The activity to which this WindowManager is attached
     */
    public GlideBackgroundManager(Activity activity) {
        this.activity = activity;
        prepareBackgroundManager();
        ;
    }

    public void loadImage(String imageUrl) {
        mBackgroundUri = imageUrl;
        startBackgroundTimer();
    }

    private void prepareBackgroundManager() {
        mBackgroundManager = BackgroundManager.getInstance(activity);
        mBackgroundManager.attach(activity.getWindow());

        mDefaultBackground =
                new ColorDrawable(ContextCompat.getColor(activity, R.color.transparent));
        mMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
    }

    private void startBackgroundTimer() {
        if (null != mBackgroundTimer) {
            mBackgroundTimer.cancel();
        }
        mBackgroundTimer = new Timer();
        mBackgroundTimer.schedule(new UpdateBackgroundTask(), BACKGROUND_UPDATE_DELAY);
    }

    private class UpdateBackgroundTask extends TimerTask {
        @Override
        public void run() {
            mHandler.post(() -> updateBackground(mBackgroundUri));
        }
    }


    protected void updateBackground(String uri) {
        int width = mMetrics.widthPixels;
        int height = mMetrics.heightPixels;
            Glide.with(activity)
                    .load(uri)
                    .asBitmap()
                    .centerCrop()
                    .error(R.drawable.bg_default_moviedetail)
                    .into(new SimpleTarget<Bitmap>(width, height) {
                        @Override
                        public void onResourceReady(Bitmap resource,
                                                    GlideAnimation<? super Bitmap>
                                                            glideAnimation) {
                            mBackgroundManager.setBitmap(resource);
                        }

                        @Override
                        public void onLoadFailed(Exception e, Drawable errorDrawable) {
                            super.onLoadFailed(e, errorDrawable);
                            mBackgroundManager.setDrawable(MoviesApplication.getsInstance().getResources().getDrawable(R.drawable.bg_default_moviedetail));
                        }
                    });
            mBackgroundTimer.cancel();
    }

    public void cancelBackgroundChange() {
        mBackgroundUri = null;
        if (null != mBackgroundTimer) {
            mBackgroundTimer.cancel();
        }
    }


}
