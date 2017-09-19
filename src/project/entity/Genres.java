package project.entity;

import java.io.Serializable;

/**
 * Created by Stephen on 1/20/2015.
 */
public class Genres implements Serializable {
    private Long movieid;
    private String genre;

    public Long getMovieid() {
        return movieid;
    }

    public void setMovieid(Long movieid) {
        this.movieid = movieid;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genres genres = (Genres) o;

        if (genre != null ? !genre.equals(genres.genre) : genres.genre != null) return false;
        if (movieid != null ? !movieid.equals(genres.movieid) : genres.movieid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = movieid != null ? movieid.hashCode() : 0;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        return result;
    }
}
