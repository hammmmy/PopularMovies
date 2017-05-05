package org.ipforsmartobjects.apps.popularmovies.movie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import org.ipforsmartobjects.apps.popularmovies.data.FavoritesLoader;
import org.ipforsmartobjects.apps.popularmovies.data.Movie;
import org.ipforsmartobjects.apps.popularmovies.data.RepositoryContract;
import org.ipforsmartobjects.apps.popularmovies.util.Constants;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Hamid on 2/26/2017.
 */

public class MoviesPresenter implements MoviesContract.UserActionsListener {
    private static final int FAVORITES_LOADER_ID = 1;
    private final RepositoryContract.MoviesRepository mMoviesRepository;
    private final MoviesContract.View mMoviesView;
    private final LoaderManager mLoaderManager;
    private final FavoritesLoader mFavoritesLoader;
    private int mCurrentSortOrder;

    public MoviesPresenter(@NonNull MoviesContract.View moviesView,
                           @NonNull RepositoryContract.MoviesRepository repository,
                           @NonNull LoaderManager loaderManager,
                           @NonNull FavoritesLoader favoritesLoader) {
        mMoviesView = checkNotNull(moviesView, "moviesView cannot be null");
        mMoviesRepository = repository;
        mLoaderManager = loaderManager;
        mFavoritesLoader = favoritesLoader;
    }

    @Override
    public void loadMovies(boolean forceUpdate, int sortOrder) {
        mCurrentSortOrder = sortOrder;
        mMoviesView.setProgressIndicator(true);

        if (sortOrder == Constants.FAVORITES) {
            loadFavoritesFromContentProvider();
        } else {
            if (forceUpdate) {
                mMoviesRepository.clearCache();
            }

            mMoviesRepository.loadMovies(new RepositoryContract.MoviesRepository.LoadMoviesCallback() {
                @Override
                public void onMoviesLoaded(List<Movie> movies) {
                    mMoviesView.setProgressIndicator(false);
                    mMoviesView.showMovies(movies);
                }

                @Override
                public void onLoadingFailed() {
                    mMoviesView.setProgressIndicator(false);
                    mMoviesView.showErrorView();
                }
            }, sortOrder);
        }
    }

    private void loadFavoritesFromContentProvider() {
        mLoaderManager.initLoader(FAVORITES_LOADER_ID, null, new LoaderManager.LoaderCallbacks<List<Movie>>() {
            @Override
            public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
                return mFavoritesLoader;
            }

            @Override
            public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {
                if (mCurrentSortOrder == Constants.FAVORITES) {
                    mMoviesView.setProgressIndicator(false);
                    if (movies == null || movies.isEmpty()) {
                        mMoviesView.showEmptyView();
                    } else {
                        mMoviesView.showMovies(movies);
                    }
                }
            }

            @Override
            public void onLoaderReset(Loader<List<Movie>> loader) {

            }
        });
    }

    @Override
    public void openMovieDetails(@NonNull Movie requestedMovie) {
        checkNotNull(requestedMovie, "requestedMovie cannot be null!");
        mMoviesView.showMovieDetailUi(requestedMovie.getId());
    }

    @Override
    public void changeSortOrder(String newSortOrder) {

    }
}
