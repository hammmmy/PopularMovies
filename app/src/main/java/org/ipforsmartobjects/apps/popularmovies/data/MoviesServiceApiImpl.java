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

public class MoviesServiceApiImpl implements MoviesServiceApi {
    RepositoryContract.MoviesRepositoryInteractor mInteractor;

    private TheMovieDbApiHelper.TmDbApi mApi;
    private String mApiKey;

    public MoviesServiceApiImpl(RepositoryContract.MoviesRepositoryInteractor interactor) {
        mInteractor = interactor;

        mApiKey = mInteractor.getViewContext().getString(R.string.tmdb_api_key);
        mApi = TheMovieDbApiHelper.getApi();
    }

    @Override
    public void getAllMovies(final MoviesServiceCallback<List<Movie>> callback, int sortOrder) {

        switch (sortOrder) {
            case Constants.POPULAR_MOVIES:
                Call<List<Movie>> popularMovieCall = mApi.getResultsSortedByPopularity(mApiKey);
                popularMovieCall.enqueue(new Callback<List<Movie>>() {
                    @Override
                    public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                        if (response.isSuccessful()) {
                            List<Movie> movies = response.body();
                            callback.onLoaded(movies);
                        } else {
                            callback.onLoadingFailed();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Movie>> call, Throwable t) {
                        callback.onLoadingFailed();
                    }
                });
                break;
            case Constants.HIGHEST_RATED:
                Call<List<Movie>> call = mApi.getResultsSortedByRating(mApiKey);
                call.enqueue(new Callback<List<Movie>>() {
                    @Override
                    public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                        List<Movie> movies = response.body();
                        callback.onLoaded(movies);
                    }

                    @Override
                    public void onFailure(Call<List<Movie>> call, Throwable t) {
                        callback.onLoadingFailed();
                    }
                });
                break;
        }

    }

    @Override
    public void getMovie(int movieId, final MoviesServiceCallback<Movie> callback) {
        // TODO: 3/5/2017 implement movie detail
        Call<Movie> call = mApi.getMovieWithId(movieId, mApiKey);
        call.enqueue(new Callback<Movie>() {
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
