package org.ipforsmartobjects.apps.popularmovies.data;

/**
 * Created by Hamid on 3/26/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MovieResult implements Parcelable {

    public final static Creator<MovieResult> CREATOR = new Creator<MovieResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MovieResult createFromParcel(Parcel in) {
            MovieResult instance = new MovieResult();
            instance.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.results, (Movie.class.getClassLoader()));
            instance.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
            return instance;
        }

        public MovieResult[] newArray(int size) {
            return (new MovieResult[size]);
        }

    };
    private Integer page;
    private List<Movie> results = null;
    private Integer totalResults;
    private Integer totalPages;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Movie> getMovies() {
        return results;
    }

    public void setMovies(List<Movie> movies) {
        this.results = movies;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeList(results);
        dest.writeValue(totalResults);
        dest.writeValue(totalPages);
    }

    public int describeContents() {
        return 0;
    }

}