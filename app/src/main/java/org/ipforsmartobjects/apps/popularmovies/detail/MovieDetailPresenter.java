package org.ipforsmartobjects.apps.popularmovies.detail;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import org.ipforsmartobjects.apps.popularmovies.data.Movie;
import org.ipforsmartobjects.apps.popularmovies.data.RepositoryContract;
import org.ipforsmartobjects.apps.popularmovies.data.local.FavoritesPersistenceContract;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Listens to user actions from the UI ({@link MovieDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
public class MovieDetailPresenter implements MovieDetailContract.UserActionsListener {

    private final RepositoryContract.MoviesRepository mMoviesRepository;

    private final MovieDetailContract.View mMoviesDetailView;
    private final ContentResolver mContentResolver;
    private long mMovieId;
    private Movie mMovie;

    public MovieDetailPresenter(@NonNull MovieDetailContract.View movieDetailView,
                                @NonNull RepositoryContract.MoviesRepository repository,
                                @NonNull ContentResolver contentResolver) {
        mMoviesDetailView = checkNotNull(movieDetailView, "movieDetailView cannot be null!");
        mMoviesRepository = repository;
        mContentResolver = contentResolver;
    }

    @Override
    public void openMovie(long movieId) {
        mMovieId = movieId;
        if (movieId == -1) {
            mMoviesDetailView.showEmptyView();
            return;
        }

        mMoviesDetailView.setProgressIndicator(true);
        mMoviesRepository.getMovie(movieId, new RepositoryContract.MoviesRepository.GetMovieCallback() {
            @Override
            public void onMovieLoaded(Movie movie) {
                mMoviesDetailView.setProgressIndicator(false);
                if (null == movie) {
                    mMoviesDetailView.showEmptyView();
                } else {
                    mMovie = movie;
                    boolean favoriteState = isFavorite(movie.getId());
                    mMoviesDetailView.showMovie(movie);
                    mMoviesDetailView.showFavoriteState(favoriteState);
                }
            }

            @Override
            public void onLoadingFailed() {
                mMoviesDetailView.setProgressIndicator(false);
                mMoviesDetailView.showEmptyView();
            }
        });
    }

    @Override
    public void favoriteClicked() {
        boolean newState = !isFavorite(mMovieId);
        setFavorite(mMovie, newState);
        mMoviesDetailView.showFavoriteState(newState);
    }

    public void setFavorite(Movie movie, boolean enable) {

        if (enable) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(FavoritesPersistenceContract.TableFavorites.COL_ID, Long.toString(movie.getId()));
            contentValues.put(FavoritesPersistenceContract.TableFavorites.COL_TITLE, movie.getTitle());
            contentValues.put(FavoritesPersistenceContract.TableFavorites.COL_ADULT, movie.getAdult() == true ? 1 : 0);
            contentValues.put(FavoritesPersistenceContract.TableFavorites.COL_ORIGINAL_LANGUAGE, movie.getOriginalLanguage());
            contentValues.put(FavoritesPersistenceContract.TableFavorites.COL_POSTER_PATH, movie.getPosterPath());
            contentValues.put(FavoritesPersistenceContract.TableFavorites.COL_RELEASE_DATE, movie.getReleaseDate());
            contentValues.put(FavoritesPersistenceContract.TableFavorites.COL_VOTE_AVERAGE, movie.getVoteAverage());
            boolean success = mContentResolver.insert(FavoritesPersistenceContract.CONTENT_URI, contentValues) != null;
        } else {
            boolean success = mContentResolver.delete(ContentUris.withAppendedId(FavoritesPersistenceContract.CONTENT_URI, movie.getId()), null, null) > 0;
        }

    }

    public boolean isFavorite(Long movieId) {
        Cursor cursor = mContentResolver.query(ContentUris.withAppendedId(FavoritesPersistenceContract.CONTENT_URI, movieId),
                null,
                null,
                null,
                null);

        return (cursor != null && cursor.getCount() > 0);
    }
}
