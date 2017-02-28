package org.ipforsmartobjects.apps.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamid on 2/27/2017.
 */

public class MovieList implements Parcelable {

    public static final Parcelable.Creator<MovieList> CREATOR = new Parcelable.Creator<MovieList>() {
        @Override
        public MovieList createFromParcel(Parcel in) {
            return new MovieList(in);
        }

        @Override
        public MovieList[] newArray(int size) {
            return new MovieList[size];
        }
    };
    List<Movie> movies;
    boolean isEmpty;

    protected MovieList(Parcel in) {
        isEmpty = in.readByte() == 0;

        if (!isEmpty) {
            movies = new ArrayList<>();
            in.readList(movies, Movie.class.getClassLoader());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (movies == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeList(movies);
        }

    }
}
