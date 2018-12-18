package fr.bmartel.youtubetv.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.BaseCardView;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.net.URI;

import fr.bmartel.youtubetv.R;
import fr.bmartel.youtubetv.model.Episode;
import fr.bmartel.youtubetv.utils.Utils;

public class CardPresenter extends Presenter {

    private static final String TAG = CardPresenter.class.getSimpleName();

    private static Context mContext;
    private static int CARD_WIDTH = 210;
    private static int CARD_HEIGHT = 220;

    private OnItemClicked onClick;

    public interface OnItemClicked {
        void onItemClick(Episode episode);
    }

    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }

    static class ViewHolder extends Presenter.ViewHolder {
        private Episode mMovie;
        private ImageCardView mCardView;
        private Drawable mDefaultCardImage;
        private PicassoImageCardViewTarget mImageCardViewTarget;

        public ViewHolder(View view) {
            super(view);
            mCardView = (ImageCardView) view;
            mImageCardViewTarget = new PicassoImageCardViewTarget(mCardView);
            mDefaultCardImage = mContext.getResources().getDrawable(R.drawable.lb_action_bg);
        }

        public void setMovie(Episode m) {
            mMovie = m;
        }

        public Episode getMovie() {
            return mMovie;
        }

        public ImageCardView getCardView() {
            return mCardView;
        }

        public Drawable getDefaultCardImage() {
            return mDefaultCardImage;
        }

        protected void updateCardViewImage(URI uri) {
            Picasso.with(mContext)
                    .load(uri.toString())
                    .resize(Utils.convertDpToPixel(mContext, CARD_WIDTH),
                            Utils.convertDpToPixel(mContext, CARD_HEIGHT))
                    .placeholder(mDefaultCardImage)
                    .error(mDefaultCardImage)
                    .into((com.squareup.picasso.Target) mImageCardViewTarget);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        Log.d(TAG, "onCreateViewHolder");
        mContext = parent.getContext();

        ImageCardView cardView = new ImageCardView(mContext);
        cardView.setCardType(BaseCardView.CARD_TYPE_INFO_UNDER);
        cardView.setInfoVisibility(BaseCardView.CARD_REGION_VISIBLE_ALWAYS);
        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);
        cardView.setBackgroundColor(mContext.getResources().getColor(R.color.app_guidedstep_actions_background));
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
      final   Episode movie = (Episode) item;
        ((ViewHolder) viewHolder).setMovie(movie);

        Log.d(TAG, "onBindViewHolder");
        if (movie.getImageUrl() != null) {
            ((ViewHolder) viewHolder).mCardView.setTitleText(movie.getName());
            ((ViewHolder) viewHolder).mCardView.setContentText(movie.getEpisode());
            ((ViewHolder) viewHolder).mCardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT);

            if (movie.getImageUrl().length() > 0){
                ((ViewHolder) viewHolder).updateCardViewImage(URI.create(movie.getImageUrl()));
            }
            //((ViewHolder) viewHolder).mCardView.setMainImage(((ViewHolder) viewHolder).getDefaultCardImage());
        }else {

        }

    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        Log.d(TAG, "onUnbindViewHolder");
    }

    @Override
    public void onViewAttachedToWindow(Presenter.ViewHolder viewHolder) {
        // TO DO
    }

    public static class PicassoImageCardViewTarget implements com.squareup.picasso.Target {
        private ImageCardView mImageCardView;

        public PicassoImageCardViewTarget(ImageCardView imageCardView) {
            mImageCardView = imageCardView;
        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
            Drawable bitmapDrawable = new BitmapDrawable(mContext.getResources(), bitmap);
            mImageCardView.setMainImage(bitmapDrawable);
        }

        @Override
        public void onBitmapFailed(Drawable drawable) {
            mImageCardView.setMainImage(drawable);
        }

        @Override
        public void onPrepareLoad(Drawable drawable) {
            // Do nothing, default_background manager has its own transitions
        }


    }

}