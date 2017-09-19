package project.entity;

import java.io.Serializable;

/**
 * Created by Stephen on 1/17/2015.
 */
public class UserMovie implements Serializable {
    private long userId;
    private int movieid;
    private int counts;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMovie userMovie = (UserMovie) o;

        if (counts != userMovie.counts) return false;
        if (movieid != userMovie.movieid) return false;
        if (userId != userMovie.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + movieid;
        result = 31 * result + counts;
        return result;
    }
}
