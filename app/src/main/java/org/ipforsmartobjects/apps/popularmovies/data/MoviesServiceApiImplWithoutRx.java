package org.ipforsmartobjects.apps.popularmovies.data;

import android.content.Context;

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

    private final TheMovieDbApiHelper.TmDbApi mApi;
    private final String mApiKey;

    public MoviesServiceApiImplWithoutRx(Context context) {
        mApiKey = context.getString(R.string.tmdb_api_key);
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

            case Constants.UPCOMING:
                Call<MovieResult> upcomingMoviesCall = mApi.getUpcomingMovies(mApiKey);
                // retrofit 2 without Rx code
                upcomingMoviesCall.enqueue(new Callback<MovieResult>() {
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

            case Constants.NOW_PLAYING:
                Call<MovieResult> nowPlayingMoviesCall = mApi.getNowPlayingMovies(mApiKey);
                // retrofit 2 without Rx code
                nowPlayingMoviesCall.enqueue(new Callback<MovieResult>() {
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
            case Constants.FAVORITES:
                throw new UnsupportedOperationException("Favorites are retrieved from DB only");

            default:
                throw new UnsupportedOperationException("unsupported sort order : " + sortOrder);
        }

    }

    @Override
    public void getMovie(long movieId, final MoviesServiceCallback<Movie> callback) {
        Call<Movie> movie = mApi.getMovieDetails(movieId, mApiKey);

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
