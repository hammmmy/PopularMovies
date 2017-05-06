package org.ipforsmartobjects.apps.popularmovies.data;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.support.v4.content.AsyncTaskLoader;

import org.ipforsmartobjects.apps.popularmovies.data.local.FavoritesPersistenceContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamid on 5/5/2017.
 * Reference: http://www.androiddesignpatterns.com/2012/08/implementing-loaders.html
 */

public class FavoritesLoader extends AsyncTaskLoader<List<Movie>> {

    List<Movie> mMovieList = null;
    private ContentObserver mFavoritesObserver;

    public FavoritesLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (mMovieList != null) {
            deliverResult(mMovieList);
        }

        if (mFavoritesObserver == null) {
            mFavoritesObserver = new ContentObserver(new Handler()) {
                @Override
                public void onChange(boolean selfChange) {
                    onContentChanged();
                    super.onChange(selfChange);
                }
            };
            getContext().getContentResolver().registerContentObserver(FavoritesPersistenceContract.CONTENT_URI, true, mFavoritesObserver);
        }

        if (takeContentChanged() || mMovieList == null) {
            forceLoad();
        }

    }

    @Override
    public List<Movie> loadInBackground() {
        List<Movie> movies = new ArrayList<>();
        try {
            Cursor c = getContext().getContentResolver().query(FavoritesPersistenceContract.CONTENT_URI,
                    null,
                    null,
                    null,
                    null);
            if (c != null && c.getCount() > 0) {
                while (c.moveToNext()) {
                    Long movieId = Long.parseLong(c.getString(c.getColumnIndexOrThrow(FavoritesPersistenceContract.TableFavorites.COL_ID)));
                    String title = c.getString(c.getColumnIndexOrThrow(FavoritesPersistenceContract.TableFavorites.COL_TITLE));
                    boolean adult = c.getInt(c.getColumnIndexOrThrow(FavoritesPersistenceContract.TableFavorites.COL_ADULT)) == 1;
                    String language = c.getString(c.getColumnIndexOrThrow(FavoritesPersistenceContract.TableFavorites.COL_ORIGINAL_LANGUAGE));
                    String posterPath = c.getString(c.getColumnIndexOrThrow(FavoritesPersistenceContract.TableFavorites.COL_POSTER_PATH));
                    String releaseDate = c.getString(c.getColumnIndexOrThrow(FavoritesPersistenceContract.TableFavorites.COL_RELEASE_DATE));
                    Double voteAverage = Double.parseDouble(c.getString(c.getColumnIndexOrThrow(FavoritesPersistenceContract.TableFavorites.COL_VOTE_AVERAGE)));

                    Movie movie = new Movie();
                    movie.setId(movieId);
                    movie.setTitle(title);
                    movie.setOriginalLanguage(language);
                    movie.setAdult(adult);
                    movie.setPosterPath(posterPath);
                    movie.setReleaseDate(releaseDate);
                    movie.setVoteAverage(voteAverage);
                    movies.add(movie);
                }
            }
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public void deliverResult(List<Movie> data) {
        if (isReset()) {
            mMovieList = null;
        } else if (isStarted()) {
            super.deliverResult(data);
            mMovieList = data;
        }
    }

    @Override
    protected void onStopLoading() {
        // The Loader is in a stopped state, so we should attempt to cancel the
        // current load (if there is one).
        cancelLoad();

        // Note that we leave the observer as is. Loaders in a stopped state
        // should still monitor the data source for changes so that the Loader
        // will know to force a new load if it is ever started again.
    }

    @Override
    protected void onReset() {
        mMovieList = null;
        // Stop watching for favorite changes
        if (mFavoritesObserver != null) {
            getContext().getContentResolver().unregisterContentObserver(mFavoritesObserver);
            mFavoritesObserver = null;
        }

    }
}
