package org.ipforsmartobjects.apps.popularmovies.detail.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import org.ipforsmartobjects.apps.popularmovies.data.Movie.Video;
import org.ipforsmartobjects.apps.popularmovies.databinding.ListItemVideoBinding;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Hamid on 4/17/2017.
 */

public class MovieDetailVideoAdapter extends RecyclerView.Adapter<MovieDetailVideoAdapter.ViewHolder> {

    private List<Video> mVideos;
    private Context mContext;
    private Integer mRgbColor;
    private Integer mTitleColor;
    private Integer mBodyColor;

    public MovieDetailVideoAdapter(List<Video> video) {
        setList(video);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        ListItemVideoBinding binding = ListItemVideoBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Video video = mVideos.get(position);

        Picasso.with(mContext).load(video.getVideoPreviewPath())
                .error(android.R.drawable.ic_menu_report_image)
                .into(viewHolder.mListItemVideoBinding.videoPreviewImage);
        viewHolder.mListItemVideoBinding.caption.setText(video.getName());
        viewHolder.mListItemVideoBinding.source.setText(video.getSite());

        if (mTitleColor != null) {
            viewHolder.mListItemVideoBinding.caption.setTextColor(mTitleColor);
        }
        if (mBodyColor != null) {
            viewHolder.mListItemVideoBinding.caption.setTextColor(mBodyColor);
        }
    }

    public void replaceData(List<Video> videos) {
        setList(videos);
        notifyDataSetChanged();
    }

    private void setList(List<Video> videos) {
        mVideos = checkNotNull(videos);
    }

    public void setColors(Integer rgb, Integer titleColor, Integer bodyColor) {
        mRgbColor = rgb;
        mTitleColor = titleColor;
        mBodyColor = bodyColor;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }

    private Video getItem(int position) {
        return mVideos.get(position);
    }

    private void openYoutubeVideo(String id) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
            mContext.startActivity(intent);
        } catch (Exception ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + id));
            mContext.startActivity(intent);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ListItemVideoBinding mListItemVideoBinding;

        public ViewHolder(ListItemVideoBinding binding) {
            super(binding.getRoot());
            itemView.setOnClickListener(this);
            mListItemVideoBinding = binding;
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Video video = getItem(position);
            openYoutubeVideo(video.getKey());

        }
    }
}
