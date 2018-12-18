package com.vtvfillm.ui.mainactivity.customview.episode;

import android.content.Context;
import android.support.v17.leanback.widget.ImageCardView;
import android.view.ContextThemeWrapper;

import com.bumptech.glide.Glide;
import com.vtvfillm.R;
import com.vtvfillm.base.OnCardViewItemClickListener;
import com.vtvfillm.model.Episode;

public class EpisodeCardPresenter extends AbstractEpisodeCardPresenter<EpisodeCardView> {
    private OnCardViewItemClickListener onCardViewItemClickListener;

    public EpisodeCardPresenter(Context context, int cardThemeResId) {
        super(new ContextThemeWrapper(context, cardThemeResId));
    }

    public EpisodeCardPresenter(Context context) {
        this(context, R.style.EpisodeCardTheme);
    }

    //TODO: Custom design episode card here ( extends ImageCardView )
    @Override
    protected EpisodeCardView onCreateView() {
        EpisodeCardView episodeCardView = new EpisodeCardView(getContext());
        return episodeCardView;
    }


    @Override
    public void onBindViewHolder(Episode episode, final EpisodeCardView cardView) {
        cardView.updateUi(episode);
    }
}



