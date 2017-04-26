package org.ipforsmartobjects.apps.popularmovies.data;

/**
 * Created by Hamid on 3/26/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

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
            instance.recommendations = ((Recommendations) in.readValue((Recommendations.class.getClassLoader())));
            instance.similar = ((Similar) in.readValue((Similar.class.getClassLoader())));
            instance.videos = ((Videos) in.readValue((Videos.class.getClassLoader())));
            return instance;
        }

        public Movie[] newArray(int size) {
            return (new Movie[size]);
        }

    };
    public static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/w185";
    public static final String BACKDROP_BASE_URL = "http://image.tmdb.org/t/p/w342/";
    public static final String YOUTUBE_VIDEO_PREVIEW_PREFIX = "http://img.youtube.com/vi/";
    public static final String YOUTUBE_VIDEO_PREVIEW_SUFFIX = "/mqdefault.jpg"; //reference: http://stackoverflow.com/questions/7324759/how-to-display-thumbnail-of-youtube-videos-in-android
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
    private Recommendations recommendations;
    private Similar similar;
    private Videos videos;

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

    public String getGenres() {
        StringBuilder sb = new StringBuilder();
        if (genres != null) {
            for (Movie.Genre genre : genres) {
                sb.append(genre.getName()).append(" ");
            }
            return sb.toString();
        }
        return "";
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

    public String getRuntime() {
        if (runtime == null) {
            return "";
        }
        long hours = runtime / 60;
        long minutes = runtime % 60;

        return "" + hours + "h " + minutes + "m";
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
        return (tagline == null) ? "" : tagline;
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

    public String getVoteAverage() {
        return "" + voteAverage;
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

    public Recommendations getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(Recommendations recommendations) {
        this.recommendations = recommendations;
    }

    public Similar getSimilar() {
        return similar;
    }

    public void setSimilar(Similar similar) {
        this.similar = similar;
    }

    public Videos getVideos() {
        return videos;
    }

    public void setVideos(Videos videos) {
        this.videos = videos;
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
        dest.writeValue(recommendations);
        dest.writeValue(similar);
        dest.writeValue(videos);
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
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public Genre[] newArray(int size) {
                return (new Genre[size]);
            }

        };
        private String name;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void writeToParcel(Parcel dest, int flags) {
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
                in.readList(instance.youtube, (Trailer.class.getClassLoader()));
                return instance;
            }

            public Trailers[] newArray(int size) {
                return (new Trailers[size]);
            }

        };
        private List<Trailer> youtube = null;

        public List<Trailer> getYoutube() {
            return youtube;
        }

        public void setYoutube(List<Trailer> youtube) {
            this.youtube = youtube;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(youtube);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class Trailer implements Parcelable {
        public final static Parcelable.Creator<Trailer> CREATOR = new Creator<Trailer>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Trailer createFromParcel(Parcel in) {
                Trailer instance = new Trailer();
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                instance.source = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public Trailer[] newArray(int size) {
                return (new Trailer[size]);
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

        public String getTrailerPreviewPath() {
            return TextUtils.isEmpty(source) ? null : YOUTUBE_VIDEO_PREVIEW_PREFIX + source + YOUTUBE_VIDEO_PREVIEW_SUFFIX;
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
                in.readList(instance.results, (Review.class.getClassLoader()));
                return instance;
            }

            public Reviews[] newArray(int size) {
                return (new Reviews[size]);
            }

        };
        private List<Review> results = null;

        public List<Review> getResults() {
            return results;
        }

        public void setResults(List<Review> results) {
            this.results = results;
        }


        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(results);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class Review implements Parcelable {

        public final static Parcelable.Creator<Review> CREATOR = new Creator<Review>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Review createFromParcel(Parcel in) {
                Review instance = new Review();
                instance.author = ((String) in.readValue((String.class.getClassLoader())));
                instance.content = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public Review[] newArray(int size) {
                return (new Review[size]);
            }

        };
        private String author;
        private String content;


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


        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(author);
            dest.writeValue(content);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class Picture implements Parcelable {

        public final static Parcelable.Creator<Picture> CREATOR = new Creator<Picture>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Picture createFromParcel(Parcel in) {
                Picture instance = new Picture();
                instance.aspectRatio = ((Double) in.readValue((Double.class.getClassLoader())));
                instance.filePath = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public Picture[] newArray(int size) {
                return (new Picture[size]);
            }

        };
        private Double aspectRatio;
        private String filePath;

        public Double getAspectRatio() {
            return aspectRatio;
        }

        public void setAspectRatio(Double aspectRatio) {
            this.aspectRatio = aspectRatio;
        }

        public String getFilePath() {
            return POSTER_BASE_URL + filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }


        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(aspectRatio);
            dest.writeValue(filePath);
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
            return POSTER_BASE_URL + profilePath;
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
                in.readList(instance.backdrops, (Picture.class.getClassLoader()));
                in.readList(instance.posters, (Picture.class.getClassLoader()));
                return instance;
            }

            public Images[] newArray(int size) {
                return (new Images[size]);
            }

        };
        private List<Picture> backdrops = null;
        private List<Picture> posters = null;

        public List<Picture> getBackdrops() {
            return backdrops;
        }

        public void setBackdrops(List<Picture> backdrops) {
            this.backdrops = backdrops;
        }

        public List<Picture> getPosters() {
            return posters;
        }

        public void setPosters(List<Picture> posters) {
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

    public static class Recommendations implements Parcelable {
        public final static Parcelable.Creator<Recommendations> CREATOR = new Creator<Recommendations>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Recommendations createFromParcel(Parcel in) {
                Recommendations instance = new Recommendations();
                in.readList(instance.results, (Result.class.getClassLoader()));
                return instance;
            }

            public Recommendations[] newArray(int size) {
                return (new Recommendations[size]);
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

    // kind of movie object .. can be used for Recommendations and Similar Movies
    public static class Result implements Parcelable {
        public final static Parcelable.Creator<Result> CREATOR = new Creator<Result>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Result createFromParcel(Parcel in) {
                Result instance = new Result();
                instance.adult = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                instance.backdropPath = ((String) in.readValue((String.class.getClassLoader())));
                in.readList(instance.genreIds, (java.lang.Long.class.getClassLoader()));
                instance.id = ((Long) in.readValue((Long.class.getClassLoader())));
                instance.originalLanguage = ((String) in.readValue((String.class.getClassLoader())));
                instance.originalTitle = ((String) in.readValue((String.class.getClassLoader())));
                instance.overview = ((String) in.readValue((String.class.getClassLoader())));
                instance.releaseDate = ((String) in.readValue((String.class.getClassLoader())));
                instance.posterPath = ((String) in.readValue((String.class.getClassLoader())));
                instance.popularity = ((Double) in.readValue((Double.class.getClassLoader())));
                instance.title = ((String) in.readValue((String.class.getClassLoader())));
                instance.video = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                instance.voteAverage = ((Double) in.readValue((Double.class.getClassLoader())));
                instance.voteCount = ((Long) in.readValue((Long.class.getClassLoader())));
                return instance;
            }

            public Result[] newArray(int size) {
                return (new Result[size]);
            }

        };
        private Boolean adult;
        private String backdropPath;
        private List<Long> genreIds = null;
        private Long id;
        private String originalLanguage;
        private String originalTitle;
        private String overview;
        private String releaseDate;
        private String posterPath;
        private Double popularity;
        private String title;
        private Boolean video;
        private Double voteAverage;
        private Long voteCount;

        public Boolean getAdult() {
            return adult;
        }

        public void setAdult(Boolean adult) {
            this.adult = adult;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public void setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
        }

        public List<Long> getGenreIds() {
            return genreIds;
        }

        public void setGenreIds(List<Long> genreIds) {
            this.genreIds = genreIds;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public Double getPopularity() {
            return popularity;
        }

        public void setPopularity(Double popularity) {
            this.popularity = popularity;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Boolean getVideo() {
            return video;
        }

        public void setVideo(Boolean video) {
            this.video = video;
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

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(adult);
            dest.writeValue(backdropPath);
            dest.writeList(genreIds);
            dest.writeValue(id);
            dest.writeValue(originalLanguage);
            dest.writeValue(originalTitle);
            dest.writeValue(overview);
            dest.writeValue(releaseDate);
            dest.writeValue(posterPath);
            dest.writeValue(popularity);
            dest.writeValue(title);
            dest.writeValue(video);
            dest.writeValue(voteAverage);
            dest.writeValue(voteCount);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class Similar implements Parcelable {
        public final static Parcelable.Creator<Similar> CREATOR = new Creator<Similar>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Similar createFromParcel(Parcel in) {
                Similar instance = new Similar();
                in.readList(instance.results, (Result.class.getClassLoader()));
                return instance;
            }

            public Similar[] newArray(int size) {
                return (new Similar[size]);
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

    public static class Videos implements Parcelable {
        public final static Parcelable.Creator<Videos> CREATOR = new Creator<Videos>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Videos createFromParcel(Parcel in) {
                Videos instance = new Videos();
                in.readList(instance.videos, (Video.class.getClassLoader()));
                return instance;
            }

            public Videos[] newArray(int size) {
                return (new Videos[size]);
            }

        };
        private List<Video> videos = null;

        public List<Video> getVideos() {
            return videos;
        }

        public void setVideos(List<Video> videos) {
            this.videos = videos;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(videos);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class Video implements Parcelable {
        public final static Parcelable.Creator<Video> CREATOR = new Creator<Video>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Video createFromParcel(Parcel in) {
                Video instance = new Video();
                instance.key = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                instance.site = ((String) in.readValue((String.class.getClassLoader())));
                instance.size = ((Long) in.readValue((Long.class.getClassLoader())));
                instance.type = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public Video[] newArray(int size) {
                return (new Video[size]);
            }

        };
        private String key; // "key": "XaE_9pfybL4",
        private String name; // name": "Official Trailer #2 [UK]",
        private String site; // "site": "YouTube",
        private Long size; // "size": 1080,
        private String type; // "type": "Trailer"

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(key);
            dest.writeValue(name);
            dest.writeValue(site);
            dest.writeValue(size);
            dest.writeValue(type);
        }

        public int describeContents() {
            return 0;
        }

    }
}