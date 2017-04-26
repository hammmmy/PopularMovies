package org.ipforsmartobjects.apps.popularmovies.detail;

import android.content.Context;
import android.support.annotation.NonNull;

import org.ipforsmartobjects.apps.popularmovies.Injection;
import org.ipforsmartobjects.apps.popularmovies.data.Movie;
import org.ipforsmartobjects.apps.popularmovies.data.RepositoryContract;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Listens to user actions from the UI ({@link MovieDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
public class MovieDetailPresenter implements MovieDetailContract.UserActionsListener, RepositoryContract.MoviesRepositoryInteractor {

    private final RepositoryContract.MoviesRepository mMoviesRepository;

    private final MovieDetailContract.View mMoviesDetailView;

    public MovieDetailPresenter(@NonNull MovieDetailContract.View movieDetailView) {
        mMoviesDetailView = checkNotNull(movieDetailView, "movieDetailView cannot be null!");
        mMoviesRepository = Injection.provideMoviesRepository(this);
    }

    @Override
    public void openMovie(long movieId) {
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
                    showMovie(movie);
                }
            }

            @Override
            public void onLoadingFailed() {
                mMoviesDetailView.setProgressIndicator(false);
                mMoviesDetailView.showEmptyView();
            }
        });
    }

    private void showMovie(Movie movie) {
        mMoviesDetailView.showMovie(movie);
    }

    @Override
    public Context getViewContext() {
        return mMoviesDetailView.getViewContext();
    }
}
