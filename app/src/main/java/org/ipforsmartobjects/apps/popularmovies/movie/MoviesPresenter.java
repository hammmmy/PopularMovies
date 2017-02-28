package org.ipforsmartobjects.apps.popularmovies.movie;

import android.support.annotation.NonNull;

import org.ipforsmartobjects.apps.popularmovies.data.Movie;
import org.ipforsmartobjects.apps.popularmovies.data.MovieRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Hamid on 2/26/2017.
 */

public class MoviesPresenter implements MoviesContract.UserActionsListener {
    private final MoviesContract.MoviesRepository mMoviesRepository;
    private final MoviesContract.View mMoviesView;

    public MoviesPresenter(@NonNull MoviesContract.View moviesView) {
        mMoviesView = checkNotNull(moviesView, "moviesView cannot be null!");
        mMoviesRepository = new MovieRepository();
    }

    @Override
    public void loadMovies(boolean forceUpdate) {
        mMoviesView.setProgressIndicator(true);
        if (forceUpdate) {
            mMoviesRepository.refreshData();
        }

        mMoviesRepository.getMovies(new MoviesContract.MoviesRepository.LoadMoviesCallback() {
            @Override
            public void onMoviesLoaded(List<Movie> movies) {
                mMoviesView.setProgressIndicator(false);
                mMoviesView.showMovies(movies);
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
