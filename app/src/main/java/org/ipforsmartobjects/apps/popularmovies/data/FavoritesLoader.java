package org.ipforsmartobjects.apps.popularmovies.data;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.AsyncTaskLoader;

import org.ipforsmartobjects.apps.popularmovies.data.local.FavoritesPersistenceContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamid on 5/5/2017.
 */

public class FavoritesLoader extends AsyncTaskLoader<List<Movie>> {
    Context mContext;

    public FavoritesLoader(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Movie> loadInBackground() {
        List<Movie> movies = new ArrayList<>();
        try {


            Cursor c = mContext.getContentResolver().query(FavoritesPersistenceContract.CONTENT_URI,
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
}
