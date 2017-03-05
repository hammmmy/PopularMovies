package org.ipforsmartobjects.apps.popularmovies.data;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Hamid on 3/5/2017.
 */

public class MovieRepositories {
    private static RepositoryContract.MoviesRepository repository = null;

    private MovieRepositories() {
        // no instance
    }

    public static RepositoryContract.MoviesRepository getInMemoryRepoInstance(MoviesServiceApi moviesServiceApi) {
        checkNotNull(moviesServiceApi);
        if (null == repository) {
            repository = new CachedMoviesRepository(moviesServiceApi);
        }
        return repository;
    }
}
