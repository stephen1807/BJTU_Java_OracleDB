package project.logic;

import project.entity.Movies;
import project.entity.UserInfo;
import project.entity.UserTweet;
import project.network.Access;
import project.query.Searcher;
import project.service.UserInfoService;
import project.service.UserMovieService;
import project.service.UserTweetService;
import project.util.WordProcessor;
import twitter4j.*;

import java.util.*;

/**
 * Created by Stephen on 1/17/2015.
 */
public class FriendUpdate {

    private int rate = 0;

    public void checkUpdate(IDs friendIDs) {

        UserInfoService uis = UserInfoService.getInstance();

        do {
            long[] IDs = friendIDs.getIDs();

            for (long i : IDs) {
                UserInfo u = uis.getUserInfo(i);

                if (u == null) {
                    u = new UserInfo(i, new Date());
                    uis.updateUserInfo(u);
                    parseMovie(i);
                } else {

                    Date currentDate = new Date();
                    long diff = (currentDate.getTime() - u.getLastupdate().getTime());

                    if (diff > 7 * (1000 * 60 * 60 * 24)) {
                        u.setLastupdate(new Date());
                        uis.updateUserInfo(u);

                        UserTweetService uts = UserTweetService.getInstance();
                        uts.cleanUserTweet(i);

                        UserMovieService ums = UserMovieService.getInstance();
                        ums.resetCount(i);

                        parseMovie(i);
                    }
                }

            }

        } while (friendIDs.hasNext());

    }

    private void parseMovie(long userid) {

        Access acc = Access.getInstance();

        Twitter twitter = acc.getTwitterAccess();

        try {

            int limit = twitter.getRateLimitStatus().get("/statuses/user_timeline").getRemaining();

            if (rate < limit) {

                rate++;

                ResponseList<Status> tweets = twitter.getUserTimeline(userid);

                UserTweetService uts = UserTweetService.getInstance();

                UserMovieService ums = UserMovieService.getInstance();

                for (Status s : tweets) {
                    if (s.getLang().equals("en")) {

                        long uid = s.getUser().getId();

                        UserTweet ut = new UserTweet();
                        ut.setEntryId(s.getId());
                        ut.setUserId(uid);
                        ut.setTweet(s.getText());
                        ut.setTweetDate(s.getCreatedAt());

                        uts.insertUserTweet(ut);

                        WordProcessor wp = WordProcessor.getInstance();

                        String query = wp.list2String(wp.process(s.getText()));

                        List<Movies> movies = parseTweet(query);

                        int match = wp.checkKeywords(query);

                        if (movies != null) {
                            for (Movies m : movies) {

                                int temp = m.getMovieid();

                                ums.insertUserMovie(uid, temp, match);
                            }
                        }

                    }
                }
            }
        } catch (TwitterException t) {
            t.printStackTrace();
        }
    }

    private List<Movies> parseTweet(String tweet) {

        List<Movies> list;
        Searcher searcher = new Searcher();

        list = searcher.search(tweet);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        if (list.size() > 0) {
            for (Movies m : list) {
                if ((Integer.parseInt(m.getYear()) - year) > 2) {
                    list.remove(m);
                }
            }
            Collections.sort(list, new Comparator<Movies>() {

                public int compare(Movies o1, Movies o2) {

                    Integer a = Integer.parseInt(o1.getYear());
                    Integer b = Integer.parseInt(o2.getYear());

                    // 降序
                    return b.compareTo(a);
                }
            });

            if (list.size() > 3) return list.subList(0, 3);

            else return list.subList(0, list.size());

        } else {
            return null;
        }


    }

    public void resetRate() {
        this.rate = 0;
    }
}
