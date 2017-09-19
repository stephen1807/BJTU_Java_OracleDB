package project.logic;

import project.entity.UserMovie;
import project.obj.MovieStat;
import project.service.MovieService;
import project.service.UserMovieService;
import twitter4j.IDs;

import java.util.*;

/**
 * Created by Stephen on 1/18/2015.
 */
public class MovieStatCreator {

    private Map<Integer, Integer> data;

    public MovieStatCreator() {

        data = new HashMap<Integer, Integer>();
    }

    public List<MovieStat> getStat() {

        List<MovieStat> result = new ArrayList<MovieStat>();

        UserMovieService ums = UserMovieService.getInstance();

        List<UserMovie> temp = ums.getUserMovies();

        for (UserMovie um : temp) {
            match(um.getMovieid(), um.getCounts());
        }

        MovieService mss = MovieService.getInstance();

        for (int l : data.keySet()) {
            MovieStat ms = new MovieStat();
            ms.setMovieid(l);
            ms.setTitle(mss.getMovie(l).getTitle());
            ms.setCount(data.get(l));
            result.add(ms);
        }

        Collections.sort(result, Collections.reverseOrder());

        return result;
    }

    public List<MovieStat> getStat(IDs friends) {

        List<MovieStat> result = new ArrayList<MovieStat>();

        UserMovieService ums = UserMovieService.getInstance();

        do {

            long[] ids = friends.getIDs();

            for (long id : ids) {

                List<UserMovie> temp = ums.getUserMovies(id);

                for (UserMovie um : temp) {
                    match(um.getMovieid(), um.getCounts());
                }
            }

        } while (friends.hasNext());

        MovieService mss = MovieService.getInstance();

        for (int l : data.keySet()) {
            MovieStat ms = new MovieStat();
            ms.setMovieid(l);
            String title = mss.getMovie(l).getTitle();
            ms.setTitle(title);
            ms.setCount(data.get(l));
            result.add(ms);
        }

        Collections.sort(result, Collections.reverseOrder());

        return result;
    }

    private void match(int movieid, int count) {

        if (data.containsKey(movieid)) {
            // data.replace(movieid, data.get(movieid) + count);
        } else {
            data.put(movieid, count);
        }
    }
}
