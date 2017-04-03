package org.ipforsmartobjects.apps.popularmovies.detail;

import android.content.Context;
import android.support.annotation.Nullable;

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

        Context getViewContext();
    }

    interface UserActionsListener {
        void openMovie(@Nullable int movieId);
    }
}
