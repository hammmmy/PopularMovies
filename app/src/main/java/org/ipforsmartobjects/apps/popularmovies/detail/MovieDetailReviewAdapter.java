package org.ipforsmartobjects.apps.popularmovies.detail;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ipforsmartobjects.apps.popularmovies.data.Movie.Review;
import org.ipforsmartobjects.apps.popularmovies.databinding.ListItemReviewBinding;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Hamid on 4/17/2017.
 */

public class MovieDetailReviewAdapter extends RecyclerView.Adapter<MovieDetailReviewAdapter.ViewHolder> {

    private List<Review> mReviews;
    private Context mContext;

    public MovieDetailReviewAdapter(List<Review> reviews) {
        setList(reviews);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        ListItemReviewBinding binding = ListItemReviewBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Review review = mReviews.get(position);
        viewHolder.mListItemReviewBinding.author.setText(review.getAuthor());
        viewHolder.mListItemReviewBinding.content.setText(review.getContent());
    }

    public void replaceData(List<Review> movies) {
        setList(movies);
        notifyDataSetChanged();
    }

    private void setList(List<Review> reviews) {
        mReviews = checkNotNull(reviews);
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    private Review getItem(int position) {
        return mReviews.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ListItemReviewBinding mListItemReviewBinding;

        public ViewHolder(ListItemReviewBinding binding) {
            super(binding.getRoot());
            mListItemReviewBinding = binding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Review review = getItem(position);

            new AlertDialog.Builder(mContext)
                    .setTitle(review.getAuthor())
                    .setMessage(review.getContent())
                    .setCancelable(true)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        }
    }

}
