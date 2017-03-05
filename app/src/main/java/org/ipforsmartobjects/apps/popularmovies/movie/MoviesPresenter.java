package org.ipforsmartobjects.apps.popularmovies.movie;

import android.content.Context;
import android.support.annotation.NonNull;

import org.ipforsmartobjects.apps.popularmovies.Injection;
import org.ipforsmartobjects.apps.popularmovies.data.Movie;
import org.ipforsmartobjects.apps.popularmovies.data.RepositoryContract;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Hamid on 2/26/2017.
 */

public class MoviesPresenter implements MoviesContract.UserActionsListener, RepositoryContract.MoviesRepositoryInteractor {
    private final RepositoryContract.MoviesRepository mMoviesRepository;
    private final MoviesContract.View mMoviesView;

    public MoviesPresenter(@NonNull MoviesContract.View moviesView) {
        mMoviesView = checkNotNull(moviesView, "moviesView cannot be null");
        mMoviesRepository = Injection.provideMoviesRepository(this);
    }

    @Override
    public void loadMovies(boolean forceUpdate, int sortOrder) {
        mMoviesView.setProgressIndicator(true);
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
                mMoviesView.showEmptyView();
            }
        }, sortOrder);
    }

    @Override
    public void openMovieDetails(@NonNull Movie requestedMovie) {
        checkNotNull(requestedMovie, "requestedMovie cannot be null!");
        mMoviesView.showMovieDetailUi(requestedMovie.getId());
    }

    @Override
    public void changeSortOrder(String newSortOrder) {

    }

    @Override
    public Context getViewContext() {
        return mMoviesView.getViewContext();
    }

}
