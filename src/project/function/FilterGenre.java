package project.function;

import project.obj.MovieStat;
import project.service.GenreService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 1/20/2015.
 */
public class FilterGenre {

    public List<MovieStat> filterGenre(List<MovieStat> data, List<String> genre) {

        List<MovieStat> result = new ArrayList<MovieStat>();

        GenreService gs = GenreService.getInstance();

        for (int i = 0; i < data.size(); i++) {

            MovieStat ms = data.get(i);

            boolean add = false;

            List<String> movieGenres = gs.getGenres(ms.getMovieid());

            check:
            for (String g : genre) {
                for (String g1 : movieGenres) {
                    if (g1.equals(g)) {
                        add = true;
                        break check;
                    }
                }
            }

            if (add) result.add(data.get(i));

        }

        return result;

    }
}
