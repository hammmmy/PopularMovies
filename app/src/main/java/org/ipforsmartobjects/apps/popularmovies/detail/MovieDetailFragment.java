package org.ipforsmartobjects.apps.popularmovies.detail;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.ipforsmartobjects.apps.popularmovies.Injection;
import org.ipforsmartobjects.apps.popularmovies.R;
import org.ipforsmartobjects.apps.popularmovies.data.Movie;
import org.ipforsmartobjects.apps.popularmovies.databinding.FragmentMovieDetailBinding;
import org.ipforsmartobjects.apps.popularmovies.detail.adapters.MovieDetailCastAdapter;
import org.ipforsmartobjects.apps.popularmovies.detail.adapters.MovieDetailPictureAdapter;
import org.ipforsmartobjects.apps.popularmovies.detail.adapters.MovieDetailReviewAdapter;
import org.ipforsmartobjects.apps.popularmovies.detail.adapters.MovieDetailTrailerAdapter;
import org.ipforsmartobjects.apps.popularmovies.detail.adapters.MovieDetailVideoAdapter;
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
    private long mMovieId = -1;
    private MovieDetailContract.UserActionsListener mActionsListener;
    private CollapsingToolbarLayout mAppBarLayout;

    private View mProgressBar;
    private View mDetailView;
    private View mEmptyView;
    private FragmentMovieDetailBinding mBinding;
    private MovieDetailVideoAdapter mVideoAdapter;
    private MovieDetailTrailerAdapter mTrailerAdapter;
    private MovieDetailCastAdapter mCastAdapter;
    private MovieDetailReviewAdapter mReviewAdapter;
    private MovieDetailPictureAdapter mPictureAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActionsListener = new MovieDetailPresenter(this,
                Injection.provideMoviesRepository(getActivity()),
                getActivity().getContentResolver());

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mMovieId = getArguments().getLong(ARG_ITEM_ID);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentMovieDetailBinding.inflate(inflater, container, false);
        mAppBarLayout = mBinding.toolbarLayout;
        mProgressBar = mBinding.movieDetailViewLayout.progress;
        mDetailView = mBinding.movieDetailViewLayout.movieDetailContainer;
        mEmptyView = mBinding.movieDetailViewLayout.emptyView;

        Toolbar toolbar = mBinding.detailToolbar;
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        final ImageView fab = mBinding.fab;
        mActionsListener.openMovie(mMovieId);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActionsListener.favoriteClicked();
            }
        });

        return mBinding.getRoot();
    }

    @Override
    public void setProgressIndicator(boolean active) {
        mProgressBar.setVisibility(active ? View.VISIBLE : View.GONE);
        mBinding.fab.setVisibility(active ? View.GONE : View.VISIBLE);
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

        RecyclerView trailerRecyclerView = mBinding.movieDetailViewLayout.trailerLayout;
        trailerRecyclerView.setHasFixedSize(true);
        mTrailerAdapter = new MovieDetailTrailerAdapter(
                (movie.getTrailers() == null || movie.getTrailers().getYoutube() == null)
                        ? new ArrayList<>(0) : movie.getTrailers().getYoutube());
        trailerRecyclerView.setAdapter(mTrailerAdapter);

        RecyclerView castRecyclerView = mBinding.movieDetailViewLayout.castLayout;
        castRecyclerView.setHasFixedSize(true);
        mCastAdapter = new MovieDetailCastAdapter(
                (movie.getCredits() == null || movie.getCredits().getCast() == null)
                        ? new ArrayList<>(0) : movie.getCredits().getCast());
        castRecyclerView.setAdapter(mCastAdapter);

        RecyclerView reviewRecyclerView = mBinding.movieDetailViewLayout.reviewLayout;
        reviewRecyclerView.setHasFixedSize(true);
        mReviewAdapter = new MovieDetailReviewAdapter(
                (movie.getReviews() == null || movie.getReviews().getResults() == null)
                        ? new ArrayList<>(0) : movie.getReviews().getResults());
        reviewRecyclerView.setAdapter(mReviewAdapter);

        RecyclerView pictureRecyclerView = mBinding.movieDetailViewLayout.photoLayout;
        pictureRecyclerView.setHasFixedSize(true);
        mPictureAdapter = new MovieDetailPictureAdapter(
                (movie.getImages() == null || movie.getImages().getBackdrops() == null)
                        ? new ArrayList<>(0) : movie.getImages().getBackdrops());
        pictureRecyclerView.setAdapter(mPictureAdapter);

        RecyclerView videoRecyclerView = mBinding.movieDetailViewLayout.videosLayout;
        videoRecyclerView.setHasFixedSize(true);
        mVideoAdapter = new MovieDetailVideoAdapter(
                (movie.getVideos() == null || movie.getVideos().getVideos() == null)
                        ? new ArrayList<>(0) : movie.getVideos().getVideos());
        videoRecyclerView.setAdapter(mVideoAdapter);

        Picasso.with(getActivity()).load(movie.getFullPosterPath())
                .error(android.R.drawable.ic_menu_report_image)
//                .into(mBinding.movieDetailViewLayout.poster);
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        mBinding.movieDetailViewLayout.poster.setImageBitmap(bitmap);
                        Palette.from(bitmap)
                                .generate(new Palette.PaletteAsyncListener() {
                                    @Override
                                    public void onGenerated(Palette palette) {
                                        Palette.Swatch primarySwatch = palette.getLightVibrantSwatch() == null ? palette.getDarkVibrantSwatch() : palette.getLightVibrantSwatch();
                                        Palette.Swatch secondarySwatch = palette.getVibrantSwatch() == null ? palette.getMutedSwatch() : palette.getVibrantSwatch();

                                        if (primarySwatch != null) {
                                            mBinding.movieDetailViewLayout.movieDetailViewContainer.setBackgroundColor(primarySwatch.getRgb());

                                            mBinding.movieDetailViewLayout.overviewLayoutLabel.setTextColor(primarySwatch.getTitleTextColor());
                                            mBinding.movieDetailViewLayout.tagline.setTextColor(primarySwatch.getTitleTextColor());
                                            mBinding.movieDetailViewLayout.overview.setTextColor(primarySwatch.getBodyTextColor());

                                            mBinding.movieDetailViewLayout.castLayoutLabel.setTextColor(primarySwatch.getTitleTextColor());
                                            mCastAdapter.setColors(primarySwatch.getRgb(), primarySwatch.getTitleTextColor(), primarySwatch.getBodyTextColor());

                                            mBinding.movieDetailViewLayout.photoLayoutLabel.setTextColor(primarySwatch.getTitleTextColor());

                                            mBinding.movieDetailViewLayout.reviewLayoutLabel.setTextColor(primarySwatch.getTitleTextColor());
                                            mReviewAdapter.setColors(primarySwatch.getRgb(), primarySwatch.getTitleTextColor(), primarySwatch.getBodyTextColor());

                                            mBinding.movieDetailViewLayout.trailerLayoutLabel.setTextColor(primarySwatch.getTitleTextColor());
                                            mTrailerAdapter.setColors(primarySwatch.getRgb(), primarySwatch.getTitleTextColor(), primarySwatch.getBodyTextColor());

                                            mBinding.movieDetailViewLayout.videosLayoutLabel.setTextColor(primarySwatch.getTitleTextColor());
                                            mVideoAdapter.setColors(primarySwatch.getRgb(), primarySwatch.getTitleTextColor(), primarySwatch.getBodyTextColor());
                                        }

                                        if (secondarySwatch != null) {
                                            mBinding.movieDetailViewLayout.posterContainer.setBackgroundColor(secondarySwatch.getRgb());
                                            mBinding.movieDetailViewLayout.rankingLabel.setTextColor(secondarySwatch.getTitleTextColor());
                                            mBinding.movieDetailViewLayout.ranking.setTextColor(secondarySwatch.getBodyTextColor());

                                            mBinding.movieDetailViewLayout.durationLabel.setTextColor(secondarySwatch.getTitleTextColor());
                                            mBinding.movieDetailViewLayout.duration.setTextColor(secondarySwatch.getBodyTextColor());

                                            mBinding.movieDetailViewLayout.genreLabel.setTextColor(secondarySwatch.getTitleTextColor());
                                            mBinding.movieDetailViewLayout.genre.setTextColor(secondarySwatch.getBodyTextColor());

                                            mBinding.movieDetailViewLayout.releaseDateLabel.setTextColor(secondarySwatch.getTitleTextColor());
                                            mBinding.movieDetailViewLayout.releaseDate.setTextColor(secondarySwatch.getBodyTextColor());
                                        } else if (primarySwatch != null) {
                                            mBinding.movieDetailViewLayout.rankingLabel.setTextColor(primarySwatch.getTitleTextColor());
                                            mBinding.movieDetailViewLayout.ranking.setTextColor(primarySwatch.getBodyTextColor());

                                            mBinding.movieDetailViewLayout.durationLabel.setTextColor(primarySwatch.getTitleTextColor());
                                            mBinding.movieDetailViewLayout.duration.setTextColor(primarySwatch.getBodyTextColor());

                                            mBinding.movieDetailViewLayout.genreLabel.setTextColor(primarySwatch.getTitleTextColor());
                                            mBinding.movieDetailViewLayout.genre.setTextColor(primarySwatch.getBodyTextColor());

                                            mBinding.movieDetailViewLayout.releaseDateLabel.setTextColor(primarySwatch.getTitleTextColor());
                                            mBinding.movieDetailViewLayout.releaseDate.setTextColor(primarySwatch.getBodyTextColor());
                                        }
                                    }
                                });
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBinding.movieDetailViewLayout.poster.setTransitionName("poster");
        }
        Picasso.with(getActivity()).load(movie.getFullBackdropPath())
                .error(R.drawable.default_backdrop)
                .into(mBinding.backdropImage);
        mBinding.movieDetailViewLayout.ranking.setText(movie.getFormattedVoteAverage());
        mBinding.movieDetailViewLayout.duration.setText(movie.getFormattedRuntime());
        mBinding.movieDetailViewLayout.genre.setText(movie.getGenreString());
        mBinding.movieDetailViewLayout.releaseDate.setText(movie.getReleaseDate());
        mBinding.movieDetailViewLayout.tagline.setText(movie.getTagline());


    }

    @Override
    public void showFavoriteState(boolean state) {
        if (state) {
            mBinding.fab.setImageResource(R.drawable.ic_star_on);
        } else {
            mBinding.fab.setImageResource(R.drawable.ic_star_off);
        }
    }
}
