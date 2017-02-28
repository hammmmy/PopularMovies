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

    // keeping names same as tmdb names

    private String id;
    private String original_title;
    private String poster_path;
    private String overview;
    private float vote_average;
    private String release_date;

    private Movie(Parcel in) {
        id = in.readString();
        original_title = in.readString();
        poster_path = in.readString();
        overview = in.readString();
        vote_average = in.readFloat();
        release_date = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(original_title);
        parcel.writeString(poster_path);
        parcel.writeString(overview);
        parcel.writeFloat(vote_average);
        parcel.writeString(release_date);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public void setOriginalTitle(String title) {
        this.original_title = title;
    }

    public String getPosterThumbnailPath() {
        return poster_path;
    }

    public void setPosterThumbnailPath(String path) {
        this.poster_path = path;
    }

    public String getPlotSynopsis() {
        return overview;
    }

    public void setPlotSynopsis(String plotSynopsis) {
        this.overview = plotSynopsis;
    }

    public float getUserRating() {
        return vote_average;
    }

    public void setUserRating(float rating) {
        this.vote_average = rating;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(String releaseDate) {
        this.release_date = releaseDate;
    }
}
