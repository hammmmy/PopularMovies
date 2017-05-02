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

    private static final String API_KEY = "api_key";
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String MOVIE = "movie";
    private static final String POPULAR = "/popular";
    private static final String TOP_RATED = "/top_rated";
    private static final String UPCOMING = "/upcoming";
    private static final String NOW_PLAYING = "/now_playing";
    private static final String MOVIE_ID = "/{id}";
    private static final String SORT_BY_POPULARITY_QUERY = MOVIE + POPULAR;
    private static final String SORT_BY_RATING_QUERY = MOVIE + TOP_RATED;
    private static final String UPCOMING_MOVIES_QUERY = MOVIE + UPCOMING;
    private static final String NOW_PLAYING_MOVIES_QUERY = MOVIE + NOW_PLAYING;
    //    public static final String MOVIE_WITH_ID_QUERY = MOVIE + MOVIE_ID;
    private static final String MOVIE_DETAILS_QUERY = MOVIE + MOVIE_ID + "?append_to_response=videos,reviews,trailers,credits,images/*,recommendations,similar*/";

    // use Retrofit 2 without Rx
    public static TmDbApi getApi() {
        // change to camelCase
        Gson camelCaseGson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(camelCaseGson))
                .build();
        return retrofit.create(TmDbApi.class);
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
        return retrofit.create(TmDbRxApi.class);
    }

    public interface TmDbApi {
        @GET(SORT_BY_POPULARITY_QUERY)
        Call<MovieResult> getResultsSortedByPopularity(@Query(API_KEY) String apiKey);

        @GET(SORT_BY_RATING_QUERY)
        Call<MovieResult> getResultsSortedByRating(@Query(API_KEY) String apiKey);

        @GET(UPCOMING_MOVIES_QUERY)
        Call<MovieResult> getUpcomingMovies(@Query(API_KEY) String apiKey);

        @GET(NOW_PLAYING_MOVIES_QUERY)
        Call<MovieResult> getNowPlayingMovies(@Query(API_KEY) String apiKey);

//        @GET(MOVIE_WITH_ID_QUERY)
//        Call<Movie> getMovieWithId(@Path("id") long movieId, @Query(API_KEY) String apiKey);

        @GET(MOVIE_DETAILS_QUERY)
        Call<Movie> getMovieDetails(@Path("id") long movieId, @Query(API_KEY) String apiKey);
    }

    public interface TmDbRxApi {
        @GET(SORT_BY_POPULARITY_QUERY)
        Observable<MovieResult> getResultsSortedByPopularity(@Query(API_KEY) String apiKey);

        @GET(SORT_BY_RATING_QUERY)
        Observable<MovieResult> getResultsSortedByRating(@Query(API_KEY) String apiKey);

        @GET(UPCOMING_MOVIES_QUERY)
        Observable<MovieResult> getUpcomingMovies(@Query(API_KEY) String apiKey);

        @GET(NOW_PLAYING_MOVIES_QUERY)
        Observable<MovieResult> getNowPlayingMovies(@Query(API_KEY) String apiKey);

//        @GET(MOVIE_WITH_ID_QUERY)
//        Observable<Movie> getMovieWithId(@Path("id") long movieId, @Query(API_KEY) String apiKey);

        @GET(MOVIE_DETAILS_QUERY)
        Observable<Movie> getMovieDetails(@Path("id") long movieId, @Query(API_KEY) String apiKey);
    }


}
