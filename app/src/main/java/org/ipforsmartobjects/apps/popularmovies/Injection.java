package org.ipforsmartobjects.apps.popularmovies;


import org.ipforsmartobjects.apps.popularmovies.data.MovieRepositories;
import org.ipforsmartobjects.apps.popularmovies.data.MoviesServiceApiImpl;
import org.ipforsmartobjects.apps.popularmovies.data.RepositoryContract;

/**
 * Enables injection of production implementations for {@link RepositoryContract.MoviesRepository} at compile time.
 */
public class Injection {

    public static RepositoryContract.MoviesRepository provideMoviesRepository(RepositoryContract.MoviesRepositoryInteractor listener) {
        return MovieRepositories.getInMemoryRepoInstance(new MoviesServiceApiImpl(listener));
    }
}
