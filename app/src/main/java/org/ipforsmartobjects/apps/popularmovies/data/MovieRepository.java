package org.ipforsmartobjects.apps.popularmovies.data;

import android.support.annotation.NonNull;

import org.ipforsmartobjects.apps.popularmovies.movie.MoviesContract;

/**
 * Created by Hamid on 2/27/2017.
 */

public class MovieRepository implements MoviesContract.MoviesRepository {
    @Override
    public void getMovies(@NonNull LoadMoviesCallback callback) {

    }

    @Override
    public void getMovie(@NonNull String movieId, @NonNull GetMovieCallback callback) {

    }

    @Override
    public void refreshData() {

    }
}
