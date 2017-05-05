package org.ipforsmartobjects.apps.popularmovies.detail;

import org.ipforsmartobjects.apps.popularmovies.data.Movie;

/**
 * This specifies the contract between the view, presenter, and the model.
 * reference : https://codelabs.developers.google.com/codelabs/android-testing/index.html
 */
public interface MovieDetailContract {

    interface View {

        void setProgressIndicator(boolean active);

        void showEmptyView();

        void showMovie(Movie movie);

        void showFavoriteState(boolean state);

    }

    interface UserActionsListener {
        void openMovie(long movieId);

        void favoriteClicked();
    }
}
