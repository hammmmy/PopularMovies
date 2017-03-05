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
    List<Movie> mCachedMovies;

    public CachedMoviesRepository(@NonNull MoviesServiceApi moviesServiceApi) {
        mMoviesServiceApi = checkNotNull(moviesServiceApi);
    }

    @Override
    public void loadMovies(@NonNull final LoadMoviesCallback callback, @Constants.SortOrder int sortOrder) {
        checkNotNull(callback);
        // Load from API only if needed.
        if (mCachedMovies == null) {
            mMoviesServiceApi.getAllMovies(new MoviesServiceApi.MoviesServiceCallback<List<Movie>>() {
                @Override
                public void onLoaded(List<Movie> movies) {
                    mCachedMovies = ImmutableList.copyOf(movies);
                    callback.onMoviesLoaded(mCachedMovies);
                }

                @Override
                public void onLoadingFailed() {
                    callback.onLoadingFailed();
                }
            }, sortOrder);
        } else {
            callback.onMoviesLoaded(mCachedMovies);
        }
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
        mCachedMovies = null;
    }
}
