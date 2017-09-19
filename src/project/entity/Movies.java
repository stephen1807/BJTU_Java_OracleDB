package project.entity;

/**
 * Created by Stephen on 1/15/2015.
 */
public class Movies implements Comparable {
    private int movieid;
    private String title;
    private String year;
    private String imdbid;

    public Movies() {
    }

    public Movies(int movieid, String title, String year) {
        this.movieid = movieid;
        this.title = title;
        this.year = year;
        this.imdbid = null;
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbid() {
        return imdbid;
    }

    public void setImdbid(String imdbid) {
        this.imdbid = imdbid;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movies movies = (Movies) o;

        if (movieid != movies.movieid) return false;
        if (imdbid != null ? !imdbid.equals(movies.imdbid) : movies.imdbid != null) return false;
        if (title != null ? !title.equals(movies.title) : movies.title != null) return false;
        if (year != null ? !year.equals(movies.year) : movies.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = movieid;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (imdbid != null ? imdbid.hashCode() : 0);
        return result;
    }

    public int compareTo(Object o) throws ClassCastException {
        if (!(o instanceof Movies)) throw new ClassCastException();
        Movies m = (Movies) o;
        if (Integer.parseInt(this.getYear()) > Integer.parseInt(m.getYear())) return 1;
        else if (Integer.parseInt(this.getYear()) < Integer.parseInt(m.getYear())) return -1;
        else return 0;
    }
}
