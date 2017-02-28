package org.ipforsmartobjects.apps.popularmovies.service;

import android.app.IntentService;
import android.content.Intent;

import org.ipforsmartobjects.apps.popularmovies.util.TheMovieDbApiHelper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hamid on 2/26/2017.
 */

public class TmdbService extends IntentService {
    private static final String TAG = TmdbService.class.getSimpleName();
    private TheMovieDbApiHelper.TmDbApi mApi;

    public TmdbService(String name) {
        super(name);
    }

    public TmdbService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        mApi = getApi();
    }

    public TheMovieDbApiHelper.TmDbApi getApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TheMovieDbApiHelper.TmDbApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TheMovieDbApiHelper.TmDbApi tmDbApi = retrofit.create(TheMovieDbApiHelper.TmDbApi.class);
        return tmDbApi;
    }
}
