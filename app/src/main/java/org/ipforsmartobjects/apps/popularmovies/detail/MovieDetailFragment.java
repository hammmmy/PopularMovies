package org.ipforsmartobjects.apps.popularmovies.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.ipforsmartobjects.apps.popularmovies.R;
import org.ipforsmartobjects.apps.popularmovies.data.Movie;
import org.ipforsmartobjects.apps.popularmovies.databinding.FragmentMovieDetailBinding;
import org.ipforsmartobjects.apps.popularmovies.movie.MovieListActivity;

/**
 * A fragment representing a single MovieItem detail screen.
 * This fragment is either contained in a {@link MovieListActivity}
 * in two-pane mode (on tablets) or a {@link MovieDetailActivity}
 * on handsets.
 */
public class MovieDetailFragment extends Fragment implements MovieDetailContract.View {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    private Movie mItem;
    private long mItemId = -1;
    private MovieDetailContract.UserActionsListener mActionsListener;
    private CollapsingToolbarLayout mAppBarLayout;

    private View mProgressBar;
    private View mDetailView;
    private View mEmptyView;
    private FragmentMovieDetailBinding mBinding;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActionsListener = new MovieDetailPresenter(this);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItemId = getArguments().getLong(ARG_ITEM_ID);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentMovieDetailBinding.inflate(inflater, container, false);
        mAppBarLayout = mBinding.toolbarLayout;
        mProgressBar = mBinding.movieDetailViewLayout.progress;
        mDetailView = mBinding.movieDetailViewLayout.overview;
        mEmptyView = mBinding.movieDetailViewLayout.emptyView;

        Toolbar toolbar = mBinding.detailToolbar;
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        final ImageView fab = mBinding.fab;
        mActionsListener.openMovie(mItemId);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setImageResource(R.drawable.ic_star_on);
                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return mBinding.getRoot();
    }

    @Override
    public void setProgressIndicator(boolean active) {
        mProgressBar.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showEmptyView() {
        mDetailView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMovie(Movie movie) {
        mDetailView.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
        mItem = movie;
        if (mAppBarLayout != null) {
            mAppBarLayout.setTitle(mItem.getOriginalTitle());
        }
        mBinding.movieDetailViewLayout.overview.setText(mItem.getOverview());
        Picasso.with(getActivity()).load(movie.getBackdropPath())
                .error(android.R.drawable.ic_menu_report_image)
                .into(mBinding.backdropImage);
    }

    @Override
    public Context getViewContext() {
        return getActivity();
    }
}
