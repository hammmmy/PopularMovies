package org.ipforsmartobjects.apps.popularmovies.movie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.ipforsmartobjects.apps.popularmovies.Injection;
import org.ipforsmartobjects.apps.popularmovies.R;
import org.ipforsmartobjects.apps.popularmovies.data.FavoritesLoader;
import org.ipforsmartobjects.apps.popularmovies.data.Movie;
import org.ipforsmartobjects.apps.popularmovies.databinding.ActivityMovieListBinding;
import org.ipforsmartobjects.apps.popularmovies.detail.MovieDetailActivity;
import org.ipforsmartobjects.apps.popularmovies.detail.MovieDetailFragment;
import org.ipforsmartobjects.apps.popularmovies.settings.PrefsActivity;
import org.ipforsmartobjects.apps.popularmovies.util.AutoFitGridRecyclerView;
import org.ipforsmartobjects.apps.popularmovies.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of MovieItems. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MovieDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MovieListActivity extends AppCompatActivity implements MoviesContract.View {
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private MovieAdapter mListAdapter;
    private MoviesContract.UserActionsListener mActionsListener;
    private final MovieItemListener mItemListener = new MovieItemListener() {
        @Override
        public void onMovieClick(Movie clickedMovie) {
            mActionsListener.openMovieDetails(clickedMovie);
        }
    };
    private SharedPreferences mSharedPrefs;
    private View mListView;
    private View mErrorView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ActivityMovieListBinding mBinding;
    private View mEmptyView;
    private View mTabletDetailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list);
        setSupportActionBar(mBinding.toolbar);

        mListView = mBinding.movieGridLayoutContainer.movieItemList;
        mErrorView = mBinding.movieGridLayoutContainer.errorView;
        mEmptyView = mBinding.movieGridLayoutContainer.emptyView;
        mSwipeRefreshLayout = mBinding.movieGridLayoutContainer.refreshLayout;
        mSwipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(MovieListActivity.this, R.color.colorPrimary),
                ContextCompat.getColor(MovieListActivity.this, R.color.colorAccent),
                ContextCompat.getColor(MovieListActivity.this, R.color.colorPrimaryDark));
        mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        getSortOrder();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mActionsListener.loadMovies(true, getSortOrder());
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        mBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieListActivity.this, PrefsActivity.class);
                intent.putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT_TITLE, true);
                startActivity(intent);
            }
        });


        AutoFitGridRecyclerView recyclerView = (AutoFitGridRecyclerView) mListView;
        assert recyclerView != null;
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20); // for faster scroll (?)
        setupRecyclerView(recyclerView);

        mTabletDetailView = findViewById(R.id.movie_item_detail_container);
        if (mTabletDetailView != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        mActionsListener = new MoviesPresenter(this,
                Injection.provideMoviesRepository(MovieListActivity.this),
                getSupportLoaderManager(), new FavoritesLoader(this));
    }

    private
    @Constants.SortOrder
    int getSortOrder() {
        int order = Integer.parseInt(mSharedPrefs.getString(Constants.SORT_ORDER, "" + Constants.POPULAR_MOVIES));
        switch (order) {
            case Constants.POPULAR_MOVIES:
                return Constants.POPULAR_MOVIES;
            case Constants.HIGHEST_RATED:
                return Constants.HIGHEST_RATED;
            case Constants.FAVORITES:
                return Constants.FAVORITES;
            case Constants.NOW_PLAYING:
                return Constants.NOW_PLAYING;
            case Constants.UPCOMING:
                return Constants.UPCOMING;
            default:
                return Constants.POPULAR_MOVIES;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        mListAdapter = new MovieAdapter(new ArrayList<>(0), mItemListener);
        recyclerView.setAdapter(mListAdapter);
    }

    @Override
    public void setProgressIndicator(boolean active) {
        mBinding.movieGridLayoutContainer.progress.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMovies(List<Movie> movies) {
        mListView.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
        if (mTwoPane) mTabletDetailView.setVisibility(View.VISIBLE);
        mListAdapter.replaceData(movies);
    }

    @Override
    public void showErrorView() {
        mListView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
        if (mTwoPane) mTabletDetailView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyView() {
        mListView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
        if (mTwoPane) mTabletDetailView.setVisibility(View.GONE);
    }

    @Override
    public void showMovieDetailUi(long movieId) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putLong(MovieDetailFragment.ARG_ITEM_ID, movieId);
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_item_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(MovieListActivity.this, MovieDetailActivity.class);
            intent.putExtra(MovieDetailFragment.ARG_ITEM_ID, movieId);
            ActivityOptionsCompat trans = ActivityOptionsCompat.makeSceneTransitionAnimation(MovieListActivity.this);
            ActivityCompat.startActivity(MovieListActivity.this, intent, trans.toBundle());
        }
    }

    private CharSequence getActivityTitle() {
        int order = getSortOrder();
        switch (order) {
            case Constants.POPULAR_MOVIES:
                return getString(R.string.popular_movies);
            case Constants.FAVORITES:
                return getString(R.string.favorite_movies);
            case Constants.HIGHEST_RATED:
                return getString(R.string.highest_rated_movies);
            case Constants.NOW_PLAYING:
                return getString(R.string.now_playing_movies);
            case Constants.UPCOMING:
                return getString(R.string.upcoming_movies);
            default:
                return getString(R.string.popular_movies);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            Intent intent = new Intent(MovieListActivity.this, PrefsActivity.class);
//            intent.putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT_TITLE, true);
//            startActivity(intent);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onResume() {
        mBinding.collapsingToolbar.setTitle(getActivityTitle());
        mBinding.parallaxImage.setImageResource(getParallaxImage(getSortOrder()));

        // can set the title as follows when CollapsingToolbarLayout is not used
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setTitle(getActivityTitle());
//        } else {
//            setTitle(getActivityTitle());
//        }

        mActionsListener.loadMovies(false, getSortOrder());
        super.onResume();
    }

    private int getParallaxImage(int sortOrder) {
        switch (sortOrder) {
            case Constants.POPULAR_MOVIES:
                return R.drawable.main_parallex_popular;
            case Constants.FAVORITES:
                return R.drawable.main_parallex_favorites;
            case Constants.HIGHEST_RATED:
                return R.drawable.main_parallex_highest_rated;
            case Constants.NOW_PLAYING:
                return R.drawable.main_parallex_now_playing;
            case Constants.UPCOMING:
                return R.drawable.main_parallex_upcoming;
        }
        return R.drawable.main_parallex_popular;
    }

    interface MovieItemListener {
        void onMovieClick(Movie clickedMovie);
    }
}
