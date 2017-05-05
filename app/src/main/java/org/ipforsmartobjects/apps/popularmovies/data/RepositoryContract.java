package org.ipforsmartobjects.apps.popularmovies.data;

import android.support.annotation.NonNull;

import org.ipforsmartobjects.apps.popularmovies.util.Constants;

import java.util.List;

/**
 * Created by Hamid on 3/5/2017.
 */

public interface RepositoryContract {
    interface MoviesRepository {
        void loadMovies(@NonNull LoadMoviesCallback callback, @Constants.SortOrder int sortOrder);

        void getMovie(long movieId, @NonNull GetMovieCallback callback); // get movie stored in cache

        void clearCache();

        interface LoadMoviesCallback {
            void onMoviesLoaded(List<Movie> Movies);

            void onLoadingFailed();
        }

        interface GetMovieCallback {
            void onMovieLoaded(Movie Movie);

            void onLoadingFailed();
        }
    }
}
