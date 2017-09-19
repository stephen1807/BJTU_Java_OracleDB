package project.obj;

/**
 * Created by Stephen on 1/17/2015.
 */
public class MovieStat implements Comparable {
    private int movieid;
    private String title;
    private int count;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int compareTo(Object o) throws ClassCastException {
        if (!(o instanceof MovieStat)) throw new ClassCastException();
        MovieStat m = (MovieStat) o;

        if (this.count > m.getCount()) return 1;
        else if (this.count < m.getCount()) return -1;
        else return 0;
    }
}
