package org.ipforsmartobjects.apps.popularmovies.movie;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import org.ipforsmartobjects.apps.popularmovies.data.Movie;
import org.ipforsmartobjects.apps.popularmovies.databinding.MovieListItemBinding;
import org.ipforsmartobjects.apps.popularmovies.movie.MovieListActivity.MovieItemListener;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Hamid on 2/26/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private final MovieItemListener mItemListener;
    private List<Movie> mMovies;
    private Context mContext;

    public MovieAdapter(List<Movie> movies, MovieItemListener itemListener) {
        setList(movies);
        mItemListener = itemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        MovieListItemBinding binding = MovieListItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding, mItemListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Movie movie = mMovies.get(position);

        Picasso.with(mContext).load(movie.getPosterPath())
                .error(android.R.drawable.ic_menu_report_image)
                .into(viewHolder.mMovieViewBinding.posterThumbnail);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            viewHolder.mMovieViewBinding.posterThumbnail.setTransitionName("poster");
        }
        viewHolder.mMovieViewBinding.movieTitle.setText(movie.getOriginalTitle());
        viewHolder.mMovieViewBinding.posterThumbnail.setContentDescription(movie.getOriginalTitle());
    }

    public void replaceData(List<Movie> movies) {
        setList(movies);
        notifyDataSetChanged();
    }

    private void setList(List<Movie> movies) {
        mMovies = checkNotNull(movies);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    private Movie getItem(int position) {
        return mMovies.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final MovieItemListener mItemListener;
        MovieListItemBinding mMovieViewBinding;

        public ViewHolder(MovieListItemBinding binding, MovieItemListener listener) {
            super(binding.getRoot());
            mItemListener = listener;
            itemView.setOnClickListener(this);
            mMovieViewBinding = binding;
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Movie movie = getItem(position);
            mItemListener.onMovieClick(movie);
        }
    }
}
