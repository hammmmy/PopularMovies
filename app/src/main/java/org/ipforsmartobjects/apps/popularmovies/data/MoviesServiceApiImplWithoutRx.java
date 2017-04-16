package org.ipforsmartobjects.apps.popularmovies.data;

import org.ipforsmartobjects.apps.popularmovies.R;
import org.ipforsmartobjects.apps.popularmovies.util.Constants;
import org.ipforsmartobjects.apps.popularmovies.util.TheMovieDbApiHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hamid on 3/5/2017.
 */

public class MoviesServiceApiImplWithoutRx implements MoviesServiceApi {
    private RepositoryContract.MoviesRepositoryInteractor mInteractor;

    private TheMovieDbApiHelper.TmDbApi mApi;
    private String mApiKey;

    public MoviesServiceApiImplWithoutRx(RepositoryContract.MoviesRepositoryInteractor interactor) {
        mInteractor = interactor;

        mApiKey = mInteractor.getViewContext().getString(R.string.tmdb_api_key);
        // retrofit 2 without Rx code
        mApi = TheMovieDbApiHelper.getApi();
    }

    @Override
    public void getAllMovies(final MoviesServiceCallback<List<Movie>> callback, int sortOrder) {

        switch (sortOrder) {
            case Constants.POPULAR_MOVIES:

                Call<MovieResult> popularMovieCall = mApi.getResultsSortedByPopularity(mApiKey);
                // retrofit 2 without Rx code
                popularMovieCall.enqueue(new Callback<MovieResult>() {
                    @Override
                    public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                        if (response.isSuccessful()) {
                            MovieResult movieResult = response.body();
                            callback.onLoaded(movieResult.getMovies());
                        } else {
                            callback.onLoadingFailed();
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResult> call, Throwable t) {
                        callback.onLoadingFailed();
                    }
                });

                break;
            case Constants.HIGHEST_RATED:
                Call<MovieResult> topRatedMovies = mApi.getResultsSortedByRating(mApiKey);
                // retrofit 2 without Rx code
                topRatedMovies.enqueue(new Callback<MovieResult>() {
                    @Override
                    public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                        if (response.isSuccessful()) {
                            MovieResult movieResult = response.body();
                            callback.onLoaded(movieResult.getMovies());
                        } else {
                            callback.onLoadingFailed();
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResult> call, Throwable t) {
                        callback.onLoadingFailed();
                    }
                });
                break;
        }

    }

    @Override
    public void getMovie(long movieId, final MoviesServiceCallback<Movie> callback) {
        // TODO: 3/5/2017 implement movie detail
        Call<Movie> movie = mApi.getMovieWithId(movieId, mApiKey);

        // retrofit 2 without Rx code
        movie.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                callback.onLoaded(movie);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                callback.onLoadingFailed();
            }
        });
    }
}
