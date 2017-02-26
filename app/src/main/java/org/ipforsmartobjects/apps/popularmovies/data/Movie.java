package org.ipforsmartobjects.apps.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hamid on 2/25/2017.
 */

public class Movie implements Parcelable {

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    private String mId;
    private String mOriginalTitle;
    private String mPosterThumbnailPath;
    private String mPlotSynopsis;
    private String mUserRating;
    private String mReleaseDate;

    private Movie(Parcel in) {
        mId = in.readString();
        mOriginalTitle = in.readString();
        mPosterThumbnailPath = in.readString();
        mPlotSynopsis = in.readString();
        mUserRating = in.readString();
        mReleaseDate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeString(mOriginalTitle);
        parcel.writeString(mPosterThumbnailPath);
        parcel.writeString(mPlotSynopsis);
        parcel.writeString(mUserRating);
        parcel.writeString(mReleaseDate);
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String title) {
        this.mOriginalTitle = title;
    }

    public String getPosterThumbnailPath() {
        return mPosterThumbnailPath;
    }

    public void setPosterThumbnailPath(String path) {
        this.mPosterThumbnailPath = path;
    }

    public String getPlotSynopsis() {
        return mPlotSynopsis;
    }

    public void setPlotSynopsis(String plotSynopsis) {
        this.mPlotSynopsis = plotSynopsis;
    }

    public String getUserRating() {
        return mUserRating;
    }

    public void setUserRating(String rating) {
        this.mUserRating = rating;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.mReleaseDate = releaseDate;
    }
}
