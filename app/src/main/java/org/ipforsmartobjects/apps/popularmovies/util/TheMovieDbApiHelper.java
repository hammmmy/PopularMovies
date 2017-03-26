package org.ipforsmartobjects.apps.popularmovies.util;

import org.ipforsmartobjects.apps.popularmovies.data.Movie;
import org.ipforsmartobjects.apps.popularmovies.data.MovieResult;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Hamid on 2/27/2017.
 */

public class TheMovieDbApiHelper {

    public static TmDbApi getApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TheMovieDbApiHelper.TmDbApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TheMovieDbApiHelper.TmDbApi tmDbApi = retrofit.create(TheMovieDbApiHelper.TmDbApi.class);
        return tmDbApi;
    }

    public interface TmDbApi {
        String BASE_URL = "https://api.themoviedb.org/3/";
        String MOVIE = "movie";
        String POPULAR = "/popular";
        String TOP_RATED = "/top_rated";

        String SORT_BY_POPULARITY_QUERY = MOVIE + POPULAR;

        String SORT_BY_RATING_QUERY = MOVIE + TOP_RATED;

        String MOVIE_WITH_ID_QUERY = MOVIE;

        final String API_KEY = "api_key";

        @GET(SORT_BY_POPULARITY_QUERY)
        Call<MovieResult> getResultsSortedByPopularity(@Query(API_KEY) String apiKey);

        @GET(SORT_BY_RATING_QUERY)
        Call<MovieResult> getResultsSortedByRating(@Query(API_KEY) String apiKey);

        @GET(MOVIE_WITH_ID_QUERY)
        Call<Movie> getMovieWithId(@Path("id") int movieId, @Query(API_KEY) String apiKey);
    }
}
