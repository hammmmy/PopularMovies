package org.ipforsmartobjects.apps.popularmovies.data;

/**
 * Created by Hamid on 3/26/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Movie implements Parcelable {
    public final static Parcelable.Creator<Movie> CREATOR = new Creator<Movie>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Movie createFromParcel(Parcel in) {
            Movie instance = new Movie();
            instance.adult = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.backdropPath = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.genres, (Genre.class.getClassLoader()));
            instance.homepage = ((String) in.readValue((String.class.getClassLoader())));
            instance.id = ((Long) in.readValue((Long.class.getClassLoader())));
            instance.imdbId = ((String) in.readValue((String.class.getClassLoader())));
            instance.originalLanguage = ((String) in.readValue((String.class.getClassLoader())));
            instance.originalTitle = ((String) in.readValue((String.class.getClassLoader())));
            instance.overview = ((String) in.readValue((String.class.getClassLoader())));
            instance.posterPath = ((String) in.readValue((String.class.getClassLoader())));
            instance.releaseDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.runtime = ((Long) in.readValue((Long.class.getClassLoader())));
            instance.status = ((String) in.readValue((String.class.getClassLoader())));
            instance.tagline = ((String) in.readValue((String.class.getClassLoader())));
            instance.title = ((String) in.readValue((String.class.getClassLoader())));
            instance.voteAverage = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.voteCount = ((Long) in.readValue((Long.class.getClassLoader())));
            instance.trailers = ((Trailers) in.readValue((Trailers.class.getClassLoader())));
            instance.reviews = ((Reviews) in.readValue((Reviews.class.getClassLoader())));
            instance.images = ((Images) in.readValue((Images.class.getClassLoader())));
            instance.credits = ((Credits) in.readValue((Credits.class.getClassLoader())));
            return instance;
        }

        public Movie[] newArray(int size) {
            return (new Movie[size]);
        }

    };
    public final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/w185";
    public final String BACKDROP_BASE_URL = "http://image.tmdb.org/t/p/w342/";
    private Boolean adult;
    private String backdropPath;
    private List<Genre> genres = null;
    private String homepage;
    private Long id;
    private String imdbId;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private String posterPath;
    private String releaseDate;
    private Long runtime;
    private String status;
    private String tagline;
    private String title;
    private Double voteAverage;
    private Long voteCount;
    private Trailers trailers;
    private Reviews reviews;
    private Images images;
    private Credits credits;

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return BACKDROP_BASE_URL + backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return POSTER_BASE_URL + posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Long getRuntime() {
        return runtime;
    }

    public void setRuntime(Long runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }

    public Trailers getTrailers() {
        return trailers;
    }

    public void setTrailers(Trailers trailers) {
        this.trailers = trailers;
    }

    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(adult);
        dest.writeValue(backdropPath);
        dest.writeList(genres);
        dest.writeValue(homepage);
        dest.writeValue(id);
        dest.writeValue(imdbId);
        dest.writeValue(originalLanguage);
        dest.writeValue(originalTitle);
        dest.writeValue(overview);
        dest.writeValue(posterPath);
        dest.writeValue(releaseDate);
        dest.writeValue(runtime);
        dest.writeValue(status);
        dest.writeValue(tagline);
        dest.writeValue(title);
        dest.writeValue(voteAverage);
        dest.writeValue(voteCount);
        dest.writeValue(trailers);
        dest.writeValue(reviews);
        dest.writeValue(images);
        dest.writeValue(credits);
    }

    public int describeContents() {
        return 0;
    }

    public static class Genre implements Parcelable {

        public final static Parcelable.Creator<Genre> CREATOR = new Creator<Genre>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Genre createFromParcel(Parcel in) {
                Genre instance = new Genre();
                instance.id = ((Long) in.readValue((Long.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public Genre[] newArray(int size) {
                return (new Genre[size]);
            }

        };
        private Long id;
        private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(name);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class Trailers implements Parcelable {

        public final static Parcelable.Creator<Trailers> CREATOR = new Creator<Trailers>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Trailers createFromParcel(Parcel in) {
                Trailers instance = new Trailers();
                in.readList(instance.youtube, (Youtube.class.getClassLoader()));
                return instance;
            }

            public Trailers[] newArray(int size) {
                return (new Trailers[size]);
            }

        };
        private List<Youtube> youtube = null;

        public List<Youtube> getYoutube() {
            return youtube;
        }

        public void setYoutube(List<Youtube> youtube) {
            this.youtube = youtube;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(youtube);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class Youtube implements Parcelable {
        public final static Parcelable.Creator<Youtube> CREATOR = new Creator<Youtube>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Youtube createFromParcel(Parcel in) {
                Youtube instance = new Youtube();
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                instance.source = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public Youtube[] newArray(int size) {
                return (new Youtube[size]);
            }

        };
        private String name;
        private String source;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(name);
            dest.writeValue(source);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class Reviews implements Parcelable {
        public final static Parcelable.Creator<Reviews> CREATOR = new Creator<Reviews>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Reviews createFromParcel(Parcel in) {
                Reviews instance = new Reviews();
                in.readList(instance.results, (Result.class.getClassLoader()));
                return instance;
            }

            public Reviews[] newArray(int size) {
                return (new Reviews[size]);
            }

        };
        private List<Result> results = null;

        public List<Result> getResults() {
            return results;
        }

        public void setResults(List<Result> results) {
            this.results = results;
        }


        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(results);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class Result implements Parcelable {

        public final static Parcelable.Creator<Result> CREATOR = new Creator<Result>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Result createFromParcel(Parcel in) {
                Result instance = new Result();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.author = ((String) in.readValue((String.class.getClassLoader())));
                instance.content = ((String) in.readValue((String.class.getClassLoader())));
                instance.url = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public Result[] newArray(int size) {
                return (new Result[size]);
            }

        };
        private String id;
        private String author;
        private String content;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(author);
            dest.writeValue(content);
            dest.writeValue(url);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class Backdrop implements Parcelable {

        //        private Long height;
//        private Long width;
        public final static Parcelable.Creator<Backdrop> CREATOR = new Creator<Backdrop>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Backdrop createFromParcel(Parcel in) {
                Backdrop instance = new Backdrop();
//                instance.aspectRatio = ((Double) in.readValue((Double.class.getClassLoader())));
                instance.filePath = ((String) in.readValue((String.class.getClassLoader())));
//                instance.height = ((Long) in.readValue((Long.class.getClassLoader())));
//                instance.width = ((Long) in.readValue((Long.class.getClassLoader())));
                return instance;
            }

            public Backdrop[] newArray(int size) {
                return (new Backdrop[size]);
            }

        };
        //        private Double aspectRatio;
        private String filePath;

//        public Double getAspectRatio() {
//            return aspectRatio;
//        }
//
//        public void setAspectRatio(Double aspectRatio) {
//            this.aspectRatio = aspectRatio;
//        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

//        public Long getHeight() {
//            return height;
//        }
//
//        public void setHeight(Long height) {
//            this.height = height;
//        }


//        public Long getWidth() {
//            return width;
//        }
//
//        public void setWidth(Long width) {
//            this.width = width;
//        }

        public void writeToParcel(Parcel dest, int flags) {
//            dest.writeValue(aspectRatio);
            dest.writeValue(filePath);
//            dest.writeValue(height);
//            dest.writeValue(width);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class Cast implements Parcelable {
        public final static Parcelable.Creator<Cast> CREATOR = new Creator<Cast>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Cast createFromParcel(Parcel in) {
                Cast instance = new Cast();
                instance.character = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                instance.profilePath = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public Cast[] newArray(int size) {
                return (new Cast[size]);
            }

        };
        private String character;
        private String name;
        private String profilePath;

        public String getCharacter() {
            return character;
        }

        public void setCharacter(String character) {
            this.character = character;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProfilePath() {
            return profilePath;
        }

        public void setProfilePath(String profilePath) {
            this.profilePath = profilePath;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(character);
            dest.writeValue(name);
            dest.writeValue(profilePath);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class Images implements Parcelable {
        public final static Parcelable.Creator<Images> CREATOR = new Creator<Images>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Images createFromParcel(Parcel in) {
                Images instance = new Images();
                in.readList(instance.backdrops, (Backdrop.class.getClassLoader()));
                in.readList(instance.posters, (Backdrop.class.getClassLoader()));
                return instance;
            }

            public Images[] newArray(int size) {
                return (new Images[size]);
            }

        };
        private List<Backdrop> backdrops = null;
        private List<Backdrop> posters = null;

        public List<Backdrop> getBackdrops() {
            return backdrops;
        }

        public void setBackdrops(List<Backdrop> backdrops) {
            this.backdrops = backdrops;
        }

        public List<Backdrop> getPosters() {
            return posters;
        }

        public void setPosters(List<Backdrop> posters) {
            this.posters = posters;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(backdrops);
            dest.writeList(posters);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class Credits implements Parcelable {
        public final static Parcelable.Creator<Credits> CREATOR = new Creator<Credits>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Credits createFromParcel(Parcel in) {
                Credits instance = new Credits();
                in.readList(instance.cast, (Cast.class.getClassLoader()));
                return instance;
            }

            public Credits[] newArray(int size) {
                return (new Credits[size]);
            }

        };
        private List<Cast> cast = null;

        public List<Cast> getCast() {
            return cast;
        }

        public void setCast(List<Cast> cast) {
            this.cast = cast;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(cast);
        }

        public int describeContents() {
            return 0;
        }

    }
}