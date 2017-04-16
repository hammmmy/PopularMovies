package org.ipforsmartobjects.apps.popularmovies.movie;

/**
 * Created by Hamid on 2/26/2017.
 */

import android.content.Context;
import android.support.annotation.NonNull;

import org.ipforsmartobjects.apps.popularmovies.data.Movie;
import org.ipforsmartobjects.apps.popularmovies.util.Constants;

import java.util.List;

/**
 * This specifies the contract between the view, presenter, and the model.
 * reference : https://codelabs.developers.google.com/codelabs/android-testing/index.html
 */
public interface MoviesContract {

    interface View {
        void setProgressIndicator(boolean active);

        void showMovies(List<Movie> movies);

        void showEmptyView();

        void showMovieDetailUi(long movieId);

        Context getViewContext();
    }

    interface UserActionsListener {
        void loadMovies(boolean forceUpdate, @Constants.SortOrder int sortOrder);

        void openMovieDetails(@NonNull Movie requestedMovie);

        void changeSortOrder(String newSortOrder);
    }

}
