package com.vtvfillm.base;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        LogUtil.e("onDestroyView---------------------" + this.getClass().getSimpleName());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        LogUtil.d("setUserVisibleHint-----------------" + isVisibleToUser + "   fragment=" + this.getClass().getSimpleName());
//        Fresco.getImagePipeline().clearMemoryCaches();
    }

    /**
     * 刷新Fragment界面
     */
    public void refreshRecyclerUi(){

    }
}
