package com.vtvfillm.dagger2.component;

import android.content.Context;

import com.vtvfillm.api.MoviesApi;
import com.vtvfillm.dagger2.module.AppModule;
import com.vtvfillm.dagger2.module.MoviesApiModule;

import dagger.Component;

@Component(modules = {AppModule.class, MoviesApiModule.class})
public interface AppComponent {

    Context getContext();

    MoviesApi getMoviesApi();
}