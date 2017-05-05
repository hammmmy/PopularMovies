package org.ipforsmartobjects.apps.popularmovies.data.local;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Hamid on 5/4/2017.
 */

public class FavoritesPersistenceContract {

    // Database schema information
    public static final String TABLE_FAVORITES = "favorites";
    // Unique authority string for the content provider
    public static final String CONTENT_AUTHORITY = "org.ipforsmartobjects.apps.popularmovies.data";
    // Base content Uri for accessing the provider
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(CONTENT_AUTHORITY)
            .appendPath(TABLE_FAVORITES)
            .build();

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private FavoritesPersistenceContract() {
    }

    public static final class TableFavorites implements BaseColumns {
        public static final String COL_ID = "_id";
        public static final String COL_TITLE = "title";
        public static final String COL_POSTER_PATH = "poster_path";
        public static final String COL_ADULT = "adult";
        public static final String COL_RELEASE_DATE = "release_date";
        public static final String COL_VOTE_AVERAGE = "vote_average";
        public static final String COL_ORIGINAL_LANGUAGE = "original_language";

    }
}
