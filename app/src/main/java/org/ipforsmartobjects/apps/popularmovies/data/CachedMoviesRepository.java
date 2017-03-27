package org.ipforsmartobjects.apps.popularmovies.data;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.google.common.collect.ImmutableList;

import org.ipforsmartobjects.apps.popularmovies.util.Constants;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Concrete implementation to load movies
 */
public class CachedMoviesRepository implements RepositoryContract.MoviesRepository {

    private final MoviesServiceApi mMoviesServiceApi;

    @VisibleForTesting
    List<Movie> mCachedPopularMovies;
    @VisibleForTesting
    List<Movie> mCachedHighestRatingMovies;

    public CachedMoviesRepository(@NonNull MoviesServiceApi moviesServiceApi) {
        mMoviesServiceApi = checkNotNull(moviesServiceApi);
    }

    @Override
    public void loadMovies(@NonNull final LoadMoviesCallback callback, @Constants.SortOrder int sortOrder) {
        checkNotNull(callback);
        // Load from API only if needed.

        switch (sortOrder) {
            case Constants.POPULAR_MOVIES:
                if (mCachedPopularMovies == null) {
                    getMovies(callback, sortOrder);
                } else {
                    callback.onMoviesLoaded(mCachedPopularMovies);
                }

                break;

            case Constants.HIGHEST_RATED:
                if (mCachedHighestRatingMovies == null) {
                    getMovies(callback, sortOrder);
                } else {
                    callback.onMoviesLoaded(mCachedHighestRatingMovies);
                }
                break;

            case Constants.FAVORITES:
                // TODO: 3/25/2017   add favorites for project 2
                break;
        }

    }

    private void getMovies(@NonNull final LoadMoviesCallback callback, @Constants.SortOrder final int sortOrder) {
        mMoviesServiceApi.getAllMovies(new MoviesServiceApi.MoviesServiceCallback<List<Movie>>() {
            @Override
            public void onLoaded(List<Movie> movies) {
                if (sortOrder == Constants.POPULAR_MOVIES) {
                    mCachedPopularMovies = ImmutableList.copyOf(movies);
                    callback.onMoviesLoaded(mCachedPopularMovies);
                } else if (sortOrder == Constants.HIGHEST_RATED) {
                    mCachedHighestRatingMovies = ImmutableList.copyOf(movies);
                    callback.onMoviesLoaded(mCachedHighestRatingMovies);
                } else if (sortOrder == Constants.FAVORITES) {
                    // TODO: 3/25/2017 add favorites for project 2
                }
            }

            @Override
            public void onLoadingFailed() {
                callback.onLoadingFailed();
            }
        }, sortOrder);
    }

    @Override
    public void getMovie(@NonNull final int movieId, @NonNull final GetMovieCallback callback) {
        checkNotNull(movieId);
        checkNotNull(callback);
        // Load movies matching the id always directly from the API.
        mMoviesServiceApi.getMovie(movieId, new MoviesServiceApi.MoviesServiceCallback<Movie>() {
            @Override
            public void onLoaded(Movie movie) {
                callback.onMovieLoaded(movie);
            }

            @Override
            public void onLoadingFailed() {
                callback.onLoadingFailed();
            }
        });
    }

    @Override
    public void clearCache() {
        mCachedPopularMovies = null;
        mCachedHighestRatingMovies = null;
    }
}
