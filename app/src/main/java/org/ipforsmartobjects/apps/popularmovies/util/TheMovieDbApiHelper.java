package org.ipforsmartobjects.apps.popularmovies.util;

import org.ipforsmartobjects.apps.popularmovies.data.MovieList;

import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Hamid on 2/27/2017.
 */

public class TheMovieDbApiHelper {

    public interface TmDbApi {
        String BASE_URL = "https://api.themoviedb.org/3";
        String MOVIE = "/movie";
        String POPULAR = "/popular";
        String TOP_RATED = "/top_rated";

        String SORT_BY_POPULARITY_QUERY = MOVIE + POPULAR;

        String SORT_BY_RATING_QUERY = MOVIE + TOP_RATED;

        final String API_KEY = "api_key";

        @GET(SORT_BY_POPULARITY_QUERY)
        void getResultsSortedByPopularity(@Query(API_KEY) String apiKey, Callback<MovieList> response);

        @GET(SORT_BY_RATING_QUERY)
        void getResultsSortedByRating(@Query(API_KEY) String apiKey, Callback<MovieList> response);
    }
}
