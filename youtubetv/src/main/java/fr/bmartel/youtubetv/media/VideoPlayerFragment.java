/*
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2016 Bertrand Martel
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package fr.bmartel.youtubetv.media;

import android.os.Bundle;
import android.support.v17.leanback.app.PlaybackOverlayFragment;
import android.support.v17.leanback.widget.Action;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ClassPresenterSelector;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.PlaybackControlsRow;
import android.support.v17.leanback.widget.PlaybackControlsRowPresenter;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;

import java.util.ArrayList;

import fr.bmartel.youtubetv.R;
import fr.bmartel.youtubetv.YoutubeTvView;
import fr.bmartel.youtubetv.model.Episode;
import fr.bmartel.youtubetv.model.VideoQuality;


public class VideoPlayerFragment extends PlaybackOverlayFragment implements
        OnItemViewClickedListener, MediaPlayerGlue.OnMediaFileFinishedPlayingListener, CardPresenter.OnItemClicked {

    public static ArrayList<String> listmovieDetail;
    public static int indexEpisode = 0;
    private MovieDetail mItems = new MovieDetail();

    public static final String TAG = "VideoPlayerFragment";
    private ArrayObjectAdapter mRowsAdapter;
    private MediaPlayerGlue mGlue;
    private YoutubeTvView youtubeTvView;

    public static VideoPlayerFragment newInstance(Bundle arguments) {
        VideoPlayerFragment fragment = new VideoPlayerFragment();
        fragment.setArguments(arguments);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        youtubeTvView = (YoutubeTvView) getActivity().findViewById(R.id.youtubetv_view);

        youtubeTvView.updateView(getArguments());

        youtubeTvView.playVideo(getArguments().getString("videoId", ""));
        indexEpisode = getArguments().getInt("index");
        listmovieDetail  =   getArguments().getStringArrayList("customer");
        mItems = (MovieDetail) getArguments().getSerializable("movieDetail");
       // mItems.addAll()
        mGlue = new VideoMediaPlayerGlue(getActivity(), this, youtubeTvView) {

            @Override
            protected void onRowChanged(PlaybackControlsRow row) {
                if (mRowsAdapter == null) return;
                mRowsAdapter.notifyArrayItemRangeChanged(0, 1);
            }
        };
        mGlue.setOnMediaFileFinishedPlayingListener(this);

        mGlue.prepareMediaForPlaying();

        setBackgroundType(PlaybackOverlayFragment.BG_DARK);
        setUpRows();
//        setOnItemViewClickedListener(new OnItemViewClickedListener() {
//                                         @Override
//                                         public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
//
//                         if (row instanceof ListRow){
//                             Episode episode = (Episode) item;
//                             if (((Episode) item).getSrc() != null){
//                                 String videoId = episode.getSrc();
//                                 videoId = videoId.substring(videoId.indexOf("=") + 1,videoId.length());
//                                 youtubeTvView.playVideo(videoId);
//                             }
//                         }else {
//                         }
//
//
//                 }
//        });


    }

    public void setUpRows() {
        ClassPresenterSelector ps = new ClassPresenterSelector();
        PlaybackControlsRowPresenter playbackControlsRowPresenter;
//        playbackControlsRowPresenter = new PlaybackControlsRowPresenter(mGlue
//                .createControlsRowAndPresenter());

        playbackControlsRowPresenter = new PlaybackControlsRowPresenter(new DescriptionPresenter(mItems.getMovieEpisodes().get(indexEpisode)));
        ps.addClassPresenter(PlaybackControlsRow.class, playbackControlsRowPresenter);

//        ps.addClassPresenter(PlaybackControlsRow.class, mGlue.createControlsRowAndPresenter());
        ps.addClassPresenter(ListRow.class, new ListRowPresenter());
        mRowsAdapter = new ArrayObjectAdapter(ps);
//        mRowsAdapter = new ArrayObjectAdapter(controlsPresenter);
        addPlaybackControlsRow();
        addOtherRows();
        setAdapter(mRowsAdapter);


    }

        @Override
    public void onStart() {
        super.onStart();
        mGlue.enableProgressUpdating(mGlue.hasValidMedia() && mGlue.isMediaPlaying());
    }

    @Override
    public void onStop() {
        super.onStop();
        mGlue.enableProgressUpdating(false);
        mGlue.reset();
//        closePlayer();
    }
    private PlaybackControlsRow mPlaybackControlsRow;


    int count = 0;


    private void addPlaybackControlsRow() {
        final PlaybackControlsRowPresenter controlsPresenter = mGlue
                .createControlsRowAndPresenter();


//        Movie movieItem = new Movie();
//        mPlaybackControlsRow = new PlaybackControlsRow(movieItem);
//        mRowsAdapter.add(mPlaybackControlsRow);
//        mRowsAdapter = new ArrayObjectAdapter(controlsPresenter);


        if (mItems.getMovieEpisodes().size() - indexEpisode >8){
            count = 8;
        }else if (mItems.getMovieEpisodes().size() - indexEpisode < 8 && mItems.getMovieEpisodes().size() - indexEpisode >0){
            count = mItems.getMovieEpisodes().size() - indexEpisode;
        }


//        mPlaybackControlsRow = new PlaybackControlsRow(mItems);
//        mRowsAdapter.add(mPlaybackControlsRow);
//        mRowsAdapter = new ArrayObjectAdapter(controlsPresenter);
//
//        for (int i = indexEpisode; i<count ;i++){
//            .add(mItems.getMovieEpisodes().get(i));
//        }

//        mGlue.setDisplay(controlsPresenter);

//        mGlue.setControlsRow(mPlaybackControlsRow);
        mRowsAdapter.add(mGlue.getControlsRow());
        setOnItemViewClickedListener(this);
    }

    @Override
    public void onItemClick(Episode episode) {

    }

    private void addOtherRows() {

        CardPresenter cardPresenter = new CardPresenter();
        cardPresenter.setOnClick(this);
        ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(cardPresenter);


//        int count = 0;

//        if (mItems.getMovieEpisodes().size() - indexEpisode >8){
//            count = 8;
//        }else if (mItems.getMovieEpisodes().size() - indexEpisode < 8 && mItems.getMovieEpisodes().size() - indexEpisode >0){
//            count = mItems.getMovieEpisodes().size() - indexEpisode;
//        }


//        for(Episode movie : mItems.getMovieEpisodes()) {
//            listRowAdapter.add(movie);
//        }

        for (int i = indexEpisode; i<count ;i++){
            listRowAdapter.add(mItems.getMovieEpisodes().get(i));
        }


        HeaderItem header = new HeaderItem(0, "Đề xuất");
        mRowsAdapter.add(new ListRow(header, listRowAdapter));


    }

    @Override
    public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                              RowPresenter.ViewHolder rowViewHolder, Row row) {
        if (!(item instanceof Action)) return;
        mGlue.onActionClicked((Action) item);
    }


    @Override
    public void onMediaFileFinishedPlaying(MediaPlayerGlue.MetaData metaData) {
        mGlue.startPlayback();
    }

    public void setPlaybackQuality(VideoQuality videoQuality) {
        youtubeTvView.setPlaybackQuality(videoQuality);
    }

    public void closePlayer() {
        youtubeTvView.closePlayer();
    }
}
