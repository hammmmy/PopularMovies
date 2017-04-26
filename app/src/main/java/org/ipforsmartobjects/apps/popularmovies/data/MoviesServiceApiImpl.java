package org.ipforsmartobjects.apps.popularmovies.data;

import org.ipforsmartobjects.apps.popularmovies.R;
import org.ipforsmartobjects.apps.popularmovies.util.Constants;
import org.ipforsmartobjects.apps.popularmovies.util.TheMovieDbApiHelper;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Hamid on 3/5/2017.
 */

public class MoviesServiceApiImpl implements MoviesServiceApi {
    private final RepositoryContract.MoviesRepositoryInteractor mInteractor;

    private final TheMovieDbApiHelper.TmDbRxApi mApi;
    private final String mApiKey;

    public MoviesServiceApiImpl(RepositoryContract.MoviesRepositoryInteractor interactor) {
        mInteractor = interactor;

        mApiKey = mInteractor.getViewContext().getString(R.string.tmdb_api_key);
        mApi = TheMovieDbApiHelper.getRxApi();
    }

    @Override
    public void getAllMovies(final MoviesServiceCallback<List<Movie>> callback, int sortOrder) {

        switch (sortOrder) {
            case Constants.POPULAR_MOVIES:
                Observable<MovieResult> popularMovies = mApi.getResultsSortedByPopularity(mApiKey);
                popularMovies.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .onErrorResumeNext(throwable -> {
                            callback.onLoadingFailed();
                        })
                        .subscribe(movieResult -> {
                            if (movieResult != null && movieResult.getMovies() != null) {
                                callback.onLoaded(movieResult.getMovies());
                            }
                        });
                break;
            case Constants.HIGHEST_RATED:
                Observable<MovieResult> topRatedMovies = mApi.getResultsSortedByRating(mApiKey);
                topRatedMovies.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .onErrorResumeNext(throwable -> {
                            callback.onLoadingFailed();
                        })
                        .subscribe(movieResult -> {
                            if (movieResult != null && movieResult.getMovies() != null) {
                                callback.onLoaded(movieResult.getMovies());
                            }
                        });

                break;
            case Constants.UPCOMING:
                Observable<MovieResult> upcomingMovies = mApi.getUpcomingMovies(mApiKey);
                upcomingMovies.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .onErrorResumeNext(throwable -> {
                            callback.onLoadingFailed();
                        })
                        .subscribe(movieResult -> {
                            if (movieResult != null && movieResult.getMovies() != null) {
                                callback.onLoaded(movieResult.getMovies());
                            }
                        });
                break;

            case Constants.NOW_PLAYING:
                Observable<MovieResult> nowPlayingMovies = mApi.getNowPlayingMovies(mApiKey);
                nowPlayingMovies.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .onErrorResumeNext(throwable -> {
                            callback.onLoadingFailed();
                        })
                        .subscribe(movieResult -> {
                            if (movieResult != null && movieResult.getMovies() != null) {
                                callback.onLoaded(movieResult.getMovies());
                            }
                        });
                break;
            case Constants.FAVORITES:
                // TODO: 4/27/2017
                break;
        }

    }

    @Override
    public void getMovie(long movieId, final MoviesServiceCallback<Movie> callback) {
        Observable<Movie> movie = mApi.getMovieDetails(movieId, mApiKey);
        movie.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(throwable -> {
                    callback.onLoadingFailed();
                })
                .subscribe(fetchedMovie -> {
                    if (fetchedMovie != null) {
                        callback.onLoaded(fetchedMovie);
                    }
                });
    }
}
