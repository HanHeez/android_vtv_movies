package com.vtvfillm.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vtvfillm.R;

public class ImageUtils {
    public static void loadImageByGlide(Context mContext, String imageUrl, ImageView imageView) {
            Glide.with(mContext)
                    .load(imageUrl)
                    .asBitmap().override(260, 400).centerCrop()
                    .into(imageView);

    }
}
