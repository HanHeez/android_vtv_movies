package com.vtvfillm.ui.mainactivity.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.vtvfillm.R;

import java.util.ArrayList;

import fr.bmartel.youtubetv.YoutubeTvFragment;
import fr.bmartel.youtubetv.media.MovieDetail;


public class PlayVideoActivity extends Activity {

    private YoutubeTvFragment mYtFragment;
    String videoId = "";
    public MovieDetail movieDetail;
    ArrayList<String> listEspisode = new ArrayList<>();
    public  int indexEpisode = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_play_video);

        Intent intent = getIntent();
        videoId =  intent.getStringExtra("videoId");
        movieDetail = (MovieDetail)intent.getExtras().getSerializable("customer");
        indexEpisode = intent.getIntExtra("index",0);
        listEspisode.clear();

        for (int i =0;i<movieDetail.getMovieEpisodes().size();i++){

            listEspisode.add(movieDetail.getMovieEpisodes().get(i).getSrc());
        }

        videoId = videoId.substring(videoId.indexOf("=") + 1,videoId.length());
        FragmentTransaction fTransaction = getFragmentManager().beginTransaction();

        Bundle args = new Bundle();
        args.putString("videoId", videoId);
        args.putStringArrayList("customer", listEspisode);
        args.putString("videoQuality", "tiny");
        args.putInt("index", indexEpisode);
        args.putBoolean("showRelatedVideos", false);
        args.putSerializable("movieDetail",movieDetail);
        mYtFragment = YoutubeTvFragment.newInstance(args);
        fTransaction.replace(R.id.youtube_fragment, mYtFragment);
        fTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mYtFragment.closePlayer();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mYtFragment.closePlayer();
        finish();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        mYtFragment.closePlayer();
        finish();
        super.onStop();
    }
}
