package org.ipforsmartobjects.apps.popularmovies.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.NonNull;

import org.ipforsmartobjects.apps.popularmovies.movie.MoviesContract;

/**
 * Created by Hamid on 2/26/2017.
 */

public class TmdbService extends IntentService implements MoviesContract.MoviesRepository {
    private static final String TAG = TmdbService.class.getSimpleName();

    public TmdbService(String name) {
        super(name);
    }

    public TmdbService() {
        super(TAG);
    }

    @Override
    public void getMovies(@NonNull LoadMoviesCallback callback) {

    }

    @Override
    public void getMovie(@NonNull String movieId, @NonNull GetMovieCallback callback) {

    }

    @Override
    public void refreshData() {

    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
