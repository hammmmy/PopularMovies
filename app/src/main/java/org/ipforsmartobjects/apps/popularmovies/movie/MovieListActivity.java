package org.ipforsmartobjects.apps.popularmovies.movie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.ipforsmartobjects.apps.popularmovies.R;
import org.ipforsmartobjects.apps.popularmovies.data.Movie;
import org.ipforsmartobjects.apps.popularmovies.detail.MovieDetailActivity;
import org.ipforsmartobjects.apps.popularmovies.detail.MovieDetailFragment;
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
    MovieItemListener mItemListener = new MovieItemListener() {
        @Override
        public void onMovieClick(Movie clickedMovie) {
            mActionsListener.openMovieDetails(clickedMovie);
        }
    };
    private View mProgressBar;
    private View mListView;
    private View mEmptyView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        mProgressBar = findViewById(R.id.progress);
        mListView = findViewById(R.id.movie_item_list);
        mEmptyView = findViewById(R.id.empty_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(MovieListActivity.this, R.color.colorPrimary),
                ContextCompat.getColor(MovieListActivity.this, R.color.colorAccent),
                ContextCompat.getColor(MovieListActivity.this, R.color.colorPrimaryDark));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mActionsListener.loadMovies(true, Constants.POPULAR_MOVIES);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        AutoFitGridRecyclerView recyclerView = (AutoFitGridRecyclerView) findViewById(R.id.movie_item_list);
        assert recyclerView != null;
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20); // for faster scroll (?)
        setupRecyclerView(recyclerView);

        if (findViewById(R.id.movie_item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        mActionsListener = new MoviesPresenter(this);

        mActionsListener.loadMovies(false, Constants.POPULAR_MOVIES);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                mActionsListener.loadMovies(false, Constants.POPULAR_MOVIES);

            }
        });
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        mListAdapter = new MovieAdapter(new ArrayList<Movie>(0), mItemListener);
        recyclerView.setAdapter(mListAdapter);
    }

    @Override
    public void setProgressIndicator(boolean active) {
        mProgressBar.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMovies(List<Movie> movies) {
        mListView.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
        mListAdapter.replaceData(movies);
    }

    @Override
    public void showEmptyView() {
        mListView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMovieDetailUi(Integer movieId) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putInt(Constants.DETAIL_MOVIE_ID, movieId);
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_item_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(MovieListActivity.this, MovieDetailActivity.class);
            intent.putExtra(Constants.DETAIL_MOVIE_ID, movieId);
            startActivity(intent);
        }
    }

    @Override
    public Context getViewContext() {
        return MovieListActivity.this;
    }

    public interface MovieItemListener {
        void onMovieClick(Movie clickedMovie);
    }

}
