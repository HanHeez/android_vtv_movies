package com.vtvfillm.ui.mainactivity.contract;

import com.vtvfillm.base.BaseContract;
import com.vtvfillm.model.Category;
import com.vtvfillm.model.Episode;

import java.util.List;

public interface EpisodeListContract {
    interface View extends BaseContract.BaseView {
        void showEpisodeList(List<Episode> episodeList);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getEpisodeList(String movieId, int page, int perPage);
    }
}
