package org.ipforsmartobjects.apps.popularmovies.data;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.google.common.collect.ImmutableList;

import org.ipforsmartobjects.apps.popularmovies.util.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @VisibleForTesting
    List<Movie> mCachedUpComingMovies;
    @VisibleForTesting
    List<Movie> mCachedNowPlayingMovies;
    @VisibleForTesting
    Map<Long, Movie> mMovieCache = new HashMap<>();


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

            case Constants.UPCOMING:
                if (mCachedUpComingMovies == null) {
                    getMovies(callback, sortOrder);
                } else {
                    callback.onMoviesLoaded(mCachedUpComingMovies);
                }
                break;

            case Constants.NOW_PLAYING:
                if (mCachedNowPlayingMovies == null) {
                    getMovies(callback, sortOrder);
                } else {
                    callback.onMoviesLoaded(mCachedNowPlayingMovies);
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
                    for (Movie popular : mCachedPopularMovies) {
                        mMovieCache.put(popular.getId(), popular);
                    }
                } else if (sortOrder == Constants.HIGHEST_RATED) {
                    mCachedHighestRatingMovies = ImmutableList.copyOf(movies);
                    callback.onMoviesLoaded(mCachedHighestRatingMovies);
                    for (Movie highestRated : mCachedHighestRatingMovies) {
                        mMovieCache.put(highestRated.getId(), highestRated);
                    }

                } else if (sortOrder == Constants.UPCOMING) {
                    mCachedUpComingMovies = ImmutableList.copyOf(movies);
                    callback.onMoviesLoaded(mCachedUpComingMovies);
                    for (Movie upcoming : mCachedUpComingMovies) {
                        mMovieCache.put(upcoming.getId(), upcoming);
                    }

                } else if (sortOrder == Constants.NOW_PLAYING) {
                    mCachedNowPlayingMovies = ImmutableList.copyOf(movies);
                    callback.onMoviesLoaded(mCachedNowPlayingMovies);
                    for (Movie nowPlaying : mCachedNowPlayingMovies) {
                        mMovieCache.put(nowPlaying.getId(), nowPlaying);
                    }

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
    public void getMovie(@NonNull final long movieId, @NonNull final GetMovieCallback callback) {
        checkNotNull(movieId);
        checkNotNull(callback);

        if (mMovieCache.get(movieId) != null) {
            // show immediately available data first
            callback.onMovieLoaded(mMovieCache.get(movieId));
        }

        // Load movie matching the id directly from the API.
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
    public void getMovieDetailExtras(@NonNull long movieId, @NonNull GetMovieCallback callback) {

    }

    @Override
    public void clearCache() {
        mCachedPopularMovies = null;
        mCachedHighestRatingMovies = null;
        mMovieCache.clear();

    }
}
