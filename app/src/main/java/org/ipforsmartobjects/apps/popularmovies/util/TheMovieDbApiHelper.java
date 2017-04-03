package org.ipforsmartobjects.apps.popularmovies.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.ipforsmartobjects.apps.popularmovies.data.Movie;
import org.ipforsmartobjects.apps.popularmovies.data.MovieResult;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Hamid on 2/27/2017.
 */

public class TheMovieDbApiHelper {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String MOVIE = "movie";
    public static final String POPULAR = "/popular";
    public static final String TOP_RATED = "/top_rated";
    public static final String MOVIE_ID = "/{id}";
    public static final String SORT_BY_POPULARITY_QUERY = MOVIE + POPULAR;
    public static final String SORT_BY_RATING_QUERY = MOVIE + TOP_RATED;
    public static final String MOVIE_WITH_ID_QUERY = MOVIE + MOVIE_ID;
    public static final String MOVIE_EXTRAS_QUERY = MOVIE + MOVIE_ID + "?append_to_response=trailers,reviews,images,release_dates,credits";
    public static final String API_KEY = "api_key";

    // use Retrofit 2 without Rx
    public static TmDbRxApi getApi() {
        // change to camelCase
        Gson camelCaseGson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(camelCaseGson))
                .build();
        TheMovieDbApiHelper.TmDbRxApi tmDbApi = retrofit.create(TheMovieDbApiHelper.TmDbRxApi.class);
        return tmDbApi;
    }

    // use Retrofit 2 with Rx 2
    public static TmDbRxApi getRxApi() {
        // change to camelCase
        Gson camelCaseGson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(camelCaseGson))
                .build();
        TheMovieDbApiHelper.TmDbRxApi tmDbApi = retrofit.create(TheMovieDbApiHelper.TmDbRxApi.class);
        return tmDbApi;
    }

    public interface TmDbApi {
        @GET(SORT_BY_POPULARITY_QUERY)
        Call<MovieResult> getResultsSortedByPopularity(@Query(API_KEY) String apiKey);

        @GET(SORT_BY_RATING_QUERY)
        Call<MovieResult> getResultsSortedByRating(@Query(API_KEY) String apiKey);

        @GET(MOVIE_WITH_ID_QUERY)
        Call<Movie> getMovieWithId(@Path("id") int movieId, @Query(API_KEY) String apiKey);

        // TODO: 3/28/2017 add movie trailers, reviews, and cast
        @GET(MOVIE_EXTRAS_QUERY)
        Call<Movie> getMovieExtraWithId(@Path("id") int movieId, @Query(API_KEY) String apiKey);
    }

    public interface TmDbRxApi {
        @GET(SORT_BY_POPULARITY_QUERY)
        Observable<MovieResult> getResultsSortedByPopularity(@Query(API_KEY) String apiKey);

        @GET(SORT_BY_RATING_QUERY)
        Observable<MovieResult> getResultsSortedByRating(@Query(API_KEY) String apiKey);

        @GET(MOVIE_WITH_ID_QUERY)
        Observable<Movie> getMovieWithId(@Path("id") int movieId, @Query(API_KEY) String apiKey);

        // TODO: 3/28/2017 add movie trailers, reviews, and cast
        @GET(MOVIE_EXTRAS_QUERY)
        Observable<Movie> getMovieExtraWithId(@Path("id") int movieId, @Query(API_KEY) String apiKey);
    }


}
