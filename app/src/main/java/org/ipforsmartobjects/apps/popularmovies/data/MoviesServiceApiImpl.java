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
    private RepositoryContract.MoviesRepositoryInteractor mInteractor;

    private TheMovieDbApiHelper.TmDbRxApi mApi;
    private String mApiKey;

    public MoviesServiceApiImpl(RepositoryContract.MoviesRepositoryInteractor interactor) {
        mInteractor = interactor;

        mApiKey = mInteractor.getViewContext().getString(R.string.tmdb_api_key);
        mApi = TheMovieDbApiHelper.getRxApi();
        // retrofit 2 without Rx code
        //mApi = TheMovieDbApiHelper.getApi();
    }

    @Override
    public void getAllMovies(final MoviesServiceCallback<List<Movie>> callback, int sortOrder) {

        switch (sortOrder) {
            case Constants.POPULAR_MOVIES:
                Observable<MovieResult> popularMovies = mApi.getResultsSortedByPopularity(mApiKey);
                popularMovies.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .onErrorReturn(throwable -> {
                            callback.onLoadingFailed();
                            return new MovieResult();
                        })
                        .subscribe(movieResult -> {
                            if (movieResult != null && movieResult.getMovies() != null) {
                                callback.onLoaded(movieResult.getMovies());
                            } else {
                                callback.onLoadingFailed();
                            }
                        });

                // retrofit 2 without Rx code
//                popularMovieCall.enqueue(new Callback<MovieResult>() {
//                    @Override
//                    public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
//                        if (response.isSuccessful()) {
//                            MovieResult movieResult = response.body();
//                            callback.onLoaded(movieResult.getMovies());
//                        } else {
//                            callback.onLoadingFailed();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<MovieResult> call, Throwable t) {
//                        callback.onLoadingFailed();
//                    }
//                });

                break;
            case Constants.HIGHEST_RATED:
                Observable<MovieResult> topRatedMovies = mApi.getResultsSortedByRating(mApiKey);
                topRatedMovies.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .onErrorReturn(throwable -> {
                            callback.onLoadingFailed();
                            return new MovieResult();
                        })
                        .subscribe(movieResult -> {
                            if (movieResult != null && movieResult.getMovies() != null) {
                                callback.onLoaded(movieResult.getMovies());
                            } else {
                                callback.onLoadingFailed();
                            }
                        });

                // retrofit 2 without Rx code
//                topRatedMovies.enqueue(new Callback<MovieResult>() {
//                    @Override
//                    public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
//                        if (response.isSuccessful()) {
//                            MovieResult movieResult = response.body();
//                            callback.onLoaded(movieResult.getMovies());
//                        } else {
//                            callback.onLoadingFailed();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<MovieResult> call, Throwable t) {
//                        callback.onLoadingFailed();
//                    }
//                });
                break;
        }

    }

    @Override
    public void getMovie(int movieId, final MoviesServiceCallback<Movie> callback) {
        // TODO: 3/5/2017 implement movie detail
        Observable<Movie> movie = mApi.getMovieWithId(movieId, mApiKey);
        movie.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(throwable -> {
                    callback.onLoadingFailed();
                    return new Movie();
                })
                .subscribe(fetchedMovie -> {
                    callback.onLoaded(fetchedMovie);
                });
        // retrofit 2 without Rx code
//        call.enqueue(new Callback<Movie>() {
//            @Override
//            public void onResponse(Call<Movie> call, Response<Movie> response) {
//                Movie movie = response.body();
//                callback.onLoaded(movie);
//            }
//
//            @Override
//            public void onFailure(Call<Movie> call, Throwable t) {
//                callback.onLoadingFailed();
//            }
//        });
    }
}
