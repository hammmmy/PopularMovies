package org.ipforsmartobjects.apps.popularmovies.detail.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import org.ipforsmartobjects.apps.popularmovies.data.Movie.Trailer;
import org.ipforsmartobjects.apps.popularmovies.databinding.ListItemTrailerBinding;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Hamid on 4/17/2017.
 */

public class MovieDetailTrailerAdapter extends RecyclerView.Adapter<MovieDetailTrailerAdapter.ViewHolder> {

    private List<Trailer> mTrailers;
    private Context mContext;
    private Integer mRgbColor;
    private Integer mTitleColor;
    private Integer mBodyColor;

    public MovieDetailTrailerAdapter(List<Trailer> trailers) {
        setList(trailers);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        ListItemTrailerBinding binding = ListItemTrailerBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Trailer trailer = mTrailers.get(position);

        Picasso.with(mContext).load(trailer.getTrailerPreviewPath())
                .error(android.R.drawable.ic_menu_report_image)
                .into(viewHolder.mListItemTrailerBinding.trailerPreviewImage);
        viewHolder.mListItemTrailerBinding.caption.setText(trailer.getName());
        if (mBodyColor != null) {
            viewHolder.mListItemTrailerBinding.caption.setTextColor(mBodyColor);
        }
    }

    public void replaceData(List<Trailer> trailers) {
        setList(trailers);
        notifyDataSetChanged();
    }

    public void setColors(Integer rgb, Integer titleColor, Integer bodyColor) {
        mRgbColor = rgb;
        mTitleColor = titleColor;
        mBodyColor = bodyColor;
        notifyDataSetChanged();
    }

    private void setList(List<Trailer> trailers) {
        mTrailers = checkNotNull(trailers);
    }

    @Override
    public int getItemCount() {
        return mTrailers.size();
    }

    private Trailer getItem(int position) {
        return mTrailers.get(position);
    }

    private void openYoutubeTrailer(String id) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
            mContext.startActivity(intent);
        } catch (Exception ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + id));
            mContext.startActivity(intent);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ListItemTrailerBinding mListItemTrailerBinding;

        public ViewHolder(ListItemTrailerBinding binding) {
            super(binding.getRoot());
            itemView.setOnClickListener(this);
            mListItemTrailerBinding = binding;
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Trailer trailer = getItem(position);
            openYoutubeTrailer(trailer.getSource());

        }
    }
}
