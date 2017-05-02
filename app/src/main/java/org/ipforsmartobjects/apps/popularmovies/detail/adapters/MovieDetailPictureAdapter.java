package org.ipforsmartobjects.apps.popularmovies.detail.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import org.ipforsmartobjects.apps.popularmovies.data.Movie.Picture;
import org.ipforsmartobjects.apps.popularmovies.databinding.ListItemPictureBinding;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Hamid on 4/17/2017.
 */

public class MovieDetailPictureAdapter extends RecyclerView.Adapter<MovieDetailPictureAdapter.ViewHolder> {

    private List<Picture> mPictures;
    private Context mContext;

    public MovieDetailPictureAdapter(List<Picture> casts) {
        setList(casts);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        ListItemPictureBinding binding = ListItemPictureBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Picture picture = mPictures.get(position);

        Picasso.with(mContext).load(picture.getFilePath())
                .error(android.R.drawable.ic_menu_report_image)
                .into(viewHolder.mListItemPictureBinding.moviePicture);
    }

    public void replaceData(List<Picture> pictures) {
        setList(pictures);
        notifyDataSetChanged();
    }

    private void setList(List<Picture> pictures) {
        mPictures = checkNotNull(pictures);
    }

    @Override
    public int getItemCount() {
        return mPictures.size();
    }

    private Picture getItem(int position) {
        return mPictures.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final ListItemPictureBinding mListItemPictureBinding;

        public ViewHolder(ListItemPictureBinding binding) {
            super(binding.getRoot());
            mListItemPictureBinding = binding;
        }
    }
}
