package org.ipforsmartobjects.apps.popularmovies.detail;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;

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
        mDetailView = mBinding.movieDetailViewLayout.movieDetailScrollView;
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
            mAppBarLayout.setTitle(mItem.getTitle());
        }
        mBinding.movieDetailViewLayout.overview.setText(mItem.getOverview());

        Picasso.with(getActivity()).load(movie.getPosterPath())
                .error(android.R.drawable.ic_menu_report_image)
                .into(mBinding.movieDetailViewLayout.poster);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBinding.movieDetailViewLayout.poster.setTransitionName("poster");
        }
        Picasso.with(getActivity()).load(movie.getBackdropPath())
                .error(R.drawable.default_backdrop)
                .into(mBinding.backdropImage);
        mBinding.movieDetailViewLayout.ranking.setText(movie.getVoteAverage());
        mBinding.movieDetailViewLayout.duration.setText(movie.getRuntime());
        mBinding.movieDetailViewLayout.genre.setText(movie.getGenres());
        mBinding.movieDetailViewLayout.releaseDate.setText(movie.getReleaseDate());
        mBinding.movieDetailViewLayout.tagline.setText(movie.getTagline());

        RecyclerView trailerRecyclerView = mBinding.movieDetailViewLayout.trailerLayout;
        trailerRecyclerView.setHasFixedSize(true);

        MovieDetailTrailerAdapter trailerAdapter = new MovieDetailTrailerAdapter(
                (movie.getTrailers() == null || movie.getTrailers().getYoutube() == null)
                        ? new ArrayList<>(0) : movie.getTrailers().getYoutube());
        trailerRecyclerView.setAdapter(trailerAdapter);

        RecyclerView castRecyclerView = mBinding.movieDetailViewLayout.castLayout;
        castRecyclerView.setHasFixedSize(true);
        MovieDetailCastAdapter castAdapter = new MovieDetailCastAdapter(
                (movie.getCredits() == null || movie.getCredits().getCast() == null)
                        ? new ArrayList<>(0) : movie.getCredits().getCast());
        castRecyclerView.setAdapter(castAdapter);

        RecyclerView reviewRecyclerView = mBinding.movieDetailViewLayout.reviewLayout;
        reviewRecyclerView.setHasFixedSize(true);
        MovieDetailReviewAdapter reviewAdapter = new MovieDetailReviewAdapter(
                (movie.getReviews() == null || movie.getReviews().getResults() == null)
                        ? new ArrayList<>(0) : movie.getReviews().getResults());
        reviewRecyclerView.setAdapter(reviewAdapter);

        RecyclerView pictureRecyclerView = mBinding.movieDetailViewLayout.photoLayout;
        pictureRecyclerView.setHasFixedSize(true);
        MovieDetailPictureAdapter pictureAdapter = new MovieDetailPictureAdapter(
                (movie.getImages() == null || movie.getImages().getBackdrops() == null)
                        ? new ArrayList<>(0) : movie.getImages().getBackdrops());
        pictureRecyclerView.setAdapter(pictureAdapter);

    }

    @Override
    public Context getViewContext() {
        return getActivity();
    }
}
