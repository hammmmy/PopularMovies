package org.ipforsmartobjects.apps.popularmovies.util;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Hamid on 3/5/2017.
 */

public class Constants {
    public static final int POPULAR_MOVIES = 1;
    public static final int HIGHEST_RATED = 2;
    public static final int UPCOMING = 3;
    public static final int NOW_PLAYING = 4;
    public static final int FAVORITES = 5;
    public static final String DETAIL_MOVIE_ID = "detail_movie_id";
    public static final String SORT_ORDER = "sort_order";

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({POPULAR_MOVIES, HIGHEST_RATED, FAVORITES, UPCOMING, NOW_PLAYING})
    public @interface SortOrder {
    }
}
