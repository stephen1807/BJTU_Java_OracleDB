package project.function;

import project.logic.FriendUpdate;
import project.logic.MovieStatCreator;
import project.network.Access;
import project.obj.MovieStat;
import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.Collections;
import java.util.List;

/**
 * Created by Stephen on 1/17/2015.
 */
public class TopMovies {

    public List<MovieStat> getTopMovies(String twitterUserName, String type) throws TwitterException {

        List<MovieStat> result;

        Access access = Access.getInstance();

        Twitter twitter = access.getTwitterAccess();

        System.out.println(">Retrieving friend IDs");

        IDs friendIDs = twitter.getFriendsIDs(twitterUserName, -1);

        FriendUpdate fu = new FriendUpdate();

        System.out.println(">Checking for new tweets");

        fu.checkUpdate(friendIDs);

        MovieStatCreator msc = new MovieStatCreator();
        if (type.equals("friends")) {

            result = msc.getStat(friendIDs);

        } else {

            result = msc.getStat();
        }

        Collections.sort(result, Collections.reverseOrder());

        if(result.size() < 50){
            return result.subList(0, result.size());
        } else{
            return result.subList(0, 50);
        }
    }
}
