package org.ipforsmartobjects.apps.popularmovies.detail;

import android.content.Context;
import android.support.annotation.Nullable;

/**
 * This specifies the contract between the view, presenter, and the model.
 * reference : https://codelabs.developers.google.com/codelabs/android-testing/index.html
 */
public interface MovieDetailContract {

    interface View {

        void setProgressIndicator(boolean active);

        void showEmptyView();

        Context getViewContext();
    }

    interface UserActionsListener {
        void openMovie(@Nullable int movieId);
    }
}
