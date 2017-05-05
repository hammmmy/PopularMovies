package org.ipforsmartobjects.apps.popularmovies.data.local;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Hamid on 5/4/2017.
 */

public class FavoritesProvider extends ContentProvider {

    private static final String TAG = FavoritesProvider.class.getSimpleName();

    private static final int FAVORITES = 200; // can be used later if we are providing all favorites
    private static final int FAVORITES_WITH_ID = 201;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(FavoritesPersistenceContract.CONTENT_AUTHORITY,
                FavoritesPersistenceContract.TABLE_FAVORITES,
                FAVORITES);

        sUriMatcher.addURI(FavoritesPersistenceContract.CONTENT_AUTHORITY,
                FavoritesPersistenceContract.TABLE_FAVORITES + "/#",
                FAVORITES_WITH_ID);
    }

    private FavoritesDBHelper mFavoritesDBHelper;

    @Override
    public boolean onCreate() {
        mFavoritesDBHelper = new FavoritesDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        // get readable database access
        final SQLiteDatabase db = mFavoritesDBHelper.getReadableDatabase();

        // Match URI
        int match = sUriMatcher.match(uri);

        // query

        Cursor rtCursor;

        switch (match) {
            case FAVORITES:
                rtCursor = db.query(FavoritesPersistenceContract.TABLE_FAVORITES,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null);
                break;
            case FAVORITES_WITH_ID:

                // selection and args
                // uri

                final int INDEX_OF_ID_TOKEN = 1;
                String id = uri.getPathSegments().get(INDEX_OF_ID_TOKEN);
                String _selection = "_id=?";
                String[] _selectionArgs = new String[]{id};

                rtCursor = db.query(FavoritesPersistenceContract.TABLE_FAVORITES,
                        projection,
                        _selection,
                        _selectionArgs,
                        null,
                        null,
                        null);

                break;
            default:
                throw new UnsupportedOperationException("Unknown URI : " + uri);
        }

        // notification
        ContentResolver resolver = getContext().getContentResolver();
        if (rtCursor != null && resolver != null) {
            rtCursor.setNotificationUri(resolver, uri);
        }

        return rtCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        // get database access
        final SQLiteDatabase db = mFavoritesDBHelper.getWritableDatabase();

        // Match URI
        int match = sUriMatcher.match(uri);

        Uri returnUri;
        switch (match) {
            case FAVORITES:
                long id = db.insert(FavoritesPersistenceContract.TABLE_FAVORITES, null, values);
                if (id > 0) {
                    // insert successful
                    returnUri = ContentUris.withAppendedId(FavoritesPersistenceContract.CONTENT_URI, id);
                } else {
                    // failed
                    throw new SQLException("Failed to insert into " + uri);
                }
                break;

            default:
                throw new UnsupportedOperationException("Unknown URI : " + uri);
        }

        // notify change
        ContentResolver resolver = getContext().getContentResolver();
        if (resolver != null) {
            resolver.notifyChange(uri, null);
        }
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        // get database access
        final SQLiteDatabase db = mFavoritesDBHelper.getWritableDatabase();

        // Match URI
        int match = sUriMatcher.match(uri);

        int noOfDeletedRows;
        switch (match) {
            case FAVORITES_WITH_ID:
                final int INDEX_OF_ID_TOKEN = 1;
                String id = uri.getPathSegments().get(INDEX_OF_ID_TOKEN);
                String _selection = "_id=?";
                String[] _selectionArgs = new String[]{id};

                noOfDeletedRows = db.delete(FavoritesPersistenceContract.TABLE_FAVORITES, _selection, _selectionArgs);
                if (noOfDeletedRows == 0) {
                    throw new SQLException("Failed to delete " + uri);
                }
                break;

            default:
                throw new UnsupportedOperationException("Unknown URI : " + uri);
        }

        // notify change
        ContentResolver resolver = getContext().getContentResolver();
        if (resolver != null) {
            resolver.notifyChange(uri, null);
        }
        return noOfDeletedRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new UnsupportedOperationException("This provider does not support update");
    }
}
