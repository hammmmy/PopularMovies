package org.ipforsmartobjects.apps.popularmovies;


import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import org.ipforsmartobjects.apps.popularmovies.data.MovieRepositories;
import org.ipforsmartobjects.apps.popularmovies.data.MoviesServiceApiImpl;
import org.ipforsmartobjects.apps.popularmovies.data.RepositoryContract;

/**
 * Enables injection of implementations for {@link RepositoryContract.MoviesRepository} at compile time.
 */
public class Injection {

    // requires support Fragment for support loader
    public static RepositoryContract.MoviesRepository provideMoviesRepository(@NonNull FragmentActivity activityContext) {
        return MovieRepositories.getInMemoryRepoInstance(new MoviesServiceApiImpl(activityContext));
    }
}
