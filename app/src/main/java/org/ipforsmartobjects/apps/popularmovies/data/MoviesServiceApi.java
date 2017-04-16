package org.ipforsmartobjects.apps.popularmovies.data;

import org.ipforsmartobjects.apps.popularmovies.util.Constants;

import java.util.List;

/**
 * Created by Hamid on 3/5/2017.
 */

public interface MoviesServiceApi {

    void getAllMovies(MoviesServiceCallback<List<Movie>> callback, @Constants.SortOrder int sortOrder);

    void getMovie(long movieId, MoviesServiceCallback<Movie> callback);

    interface MoviesServiceCallback<T> {

        void onLoaded(T movies);

        void onLoadingFailed();
    }
}