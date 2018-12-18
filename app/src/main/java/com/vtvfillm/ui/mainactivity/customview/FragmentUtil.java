package com.vtvfillm.ui.mainactivity.customview;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class FragmentUtil {

    @RequiresApi(23)
    private static Context getContextNew(Fragment fragment) {
        return fragment.getContext();
    }

    public static Context getContext(Fragment fragment) {
        if (Build.VERSION.SDK_INT >= 23) {
            return getContextNew(fragment);
        } else {
            return fragment.getActivity();
        }
    }
}
