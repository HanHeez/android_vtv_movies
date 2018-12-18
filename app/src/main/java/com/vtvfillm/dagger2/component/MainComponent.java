package com.vtvfillm.dagger2.component;

import com.vtvfillm.ui.mainactivity.activity.MainActivity;
import com.vtvfillm.ui.mainactivity.fragment.EpisodeListFragment;
import com.vtvfillm.ui.mainactivity.fragment.MainFragment;
import com.vtvfillm.ui.mainactivity.fragment.MovieDetailFragment;
import com.vtvfillm.ui.mainactivity.fragment.MovieFragment;
import com.vtvfillm.ui.mainactivity.fragment.PlayVideoFragment;
import com.vtvfillm.ui.mainactivity.fragment.SearchFragment;
import com.vtvfillm.ui.mainactivity.fragment.SubCategoryFragment;


import dagger.Component;

@Component(dependencies = AppComponent.class)
public interface MainComponent {
    MainActivity inject(MainActivity mainActivity);

    MainFragment inject(MainFragment mainFragment);

    MovieFragment inject(MovieFragment movieFragment);

    MovieDetailFragment inject(MovieDetailFragment movieDetailFragment);

    PlayVideoFragment inject(PlayVideoFragment playVideoFragment);

    EpisodeListFragment inject(EpisodeListFragment episodeListFragment);
    SubCategoryFragment inject(SubCategoryFragment subCategoryFragment);

    SearchFragment inject(SearchFragment searchFragment);

//
//    SubCategoryMovieFragment inject(SubCategoryMovieFragment subCategoryMovieFragment);
}

