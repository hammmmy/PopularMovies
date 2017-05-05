package org.ipforsmartobjects.apps.popularmovies.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hamid on 5/4/2017.
 */

public class FavoritesDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Favorites.db";
    private static final int DB_VERSION = 1;
    private static final String TAG = FavoritesDBHelper.class.getSimpleName();

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String PRIMARY_KEY = " PRIMARY KEY";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_TABLE_FAVORITES = "CREATE TABLE " +
            FavoritesPersistenceContract.TABLE_FAVORITES + " (" +
            FavoritesPersistenceContract.TableFavorites._ID + TEXT_TYPE + PRIMARY_KEY + COMMA_SEP +
            FavoritesPersistenceContract.TableFavorites.COL_TITLE + TEXT_TYPE + COMMA_SEP +
            FavoritesPersistenceContract.TableFavorites.COL_ADULT + INTEGER_TYPE + COMMA_SEP +
            FavoritesPersistenceContract.TableFavorites.COL_ORIGINAL_LANGUAGE + TEXT_TYPE + COMMA_SEP +
            FavoritesPersistenceContract.TableFavorites.COL_RELEASE_DATE + TEXT_TYPE + COMMA_SEP +
            FavoritesPersistenceContract.TableFavorites.COL_POSTER_PATH + TEXT_TYPE + COMMA_SEP +
            FavoritesPersistenceContract.TableFavorites.COL_VOTE_AVERAGE + TEXT_TYPE +
            " )";

    public FavoritesDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_FAVORITES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FavoritesPersistenceContract.TABLE_FAVORITES);
        onCreate(db);
    }
}
