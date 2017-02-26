package org.ipforsmartobjects.apps.popularmovies.movie;

/**
 * Created by Hamid on 2/26/2017.
 */

import android.support.annotation.NonNull;

import org.ipforsmartobjects.apps.popularmovies.data.Movie;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 * reference : https://codelabs.developers.google.com/codelabs/android-testing/index.html
 */
public interface MoviesContract {

    interface View {
        void setProgressIndicator(boolean active);

        void showMovies(List<Movie> movies);

        void showMovieDetailUi(String movieId);
    }

    interface UserActionsListener {
        void loadMovies(boolean forceUpdate);

        void openMovieDetails(@NonNull Movie requestedMovie);

        void changeSortOrder(String newSortOrder);
    }

    interface MoviesRepository {
        void getMovies(@NonNull LoadMoviesCallback callback);

        void getMovie(@NonNull String movieId, @NonNull MoviesRepository.GetMovieCallback callback);

        void refreshData();

        interface LoadMoviesCallback {
            void onMoviesLoaded(List<Movie> Movies);
        }

        interface GetMovieCallback {
            void onMovieLoaded(Movie Movie);
        }
    }

}
