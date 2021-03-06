package org.ipforsmartobjects.apps.popularmovies.detail.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import org.ipforsmartobjects.apps.popularmovies.data.Movie.Cast;
import org.ipforsmartobjects.apps.popularmovies.databinding.ListItemCastBinding;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Hamid on 4/17/2017.
 */

public class MovieDetailCastAdapter extends RecyclerView.Adapter<MovieDetailCastAdapter.ViewHolder> {

    private List<Cast> mCast;
    private Context mContext;
    private Integer mRgbColor;
    private Integer mTitleColor;
    private Integer mBodyColor;

    public MovieDetailCastAdapter(List<Cast> casts) {
        setList(casts);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        ListItemCastBinding binding = ListItemCastBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Cast cast = mCast.get(position);

        Picasso.with(mContext).load(cast.getProfilePath())
                .error(android.R.drawable.ic_menu_report_image)
                .into(viewHolder.mListItemCastBinding.actorImage);
        viewHolder.mListItemCastBinding.actor.setText(cast.getName());
        viewHolder.mListItemCastBinding.role.setText(cast.getCharacter());

        if (mTitleColor != null) {
            viewHolder.mListItemCastBinding.role.setTextColor(mTitleColor);
        }
        if (mBodyColor != null) {
            viewHolder.mListItemCastBinding.actor.setTextColor(mBodyColor);
        }
    }

    public void replaceData(List<Cast> casts) {
        setList(casts);
        notifyDataSetChanged();
    }

    public void setColors(Integer rgb, Integer titleColor, Integer bodyColor) {
        mRgbColor = rgb;
        mTitleColor = titleColor;
        mBodyColor = bodyColor;
        notifyDataSetChanged();
    }

    private void setList(List<Cast> casts) {
        mCast = checkNotNull(casts);
    }

    @Override
    public int getItemCount() {
        return mCast.size();
    }

    private Cast getItem(int position) {
        return mCast.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final ListItemCastBinding mListItemCastBinding;

        public ViewHolder(ListItemCastBinding binding) {
            super(binding.getRoot());
            mListItemCastBinding = binding;
        }

    }
}
