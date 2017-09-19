package project.network;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 * Created by Stephen on 2014/11/21.
 */
public class Access {

    /* Access keys. DO NOT ALTER */

    private final static String CONSUMER_KEY = "JKxjGQpNmwLkeSQxjCOeQHqjG";
    private final static String CONSUMER_KEY_SECRET = "HRx8zH6pNk5hq0jpvM1HDtXzkr4q862i8haHxgH6dt7DIo55DI";
    private final static String ACCESS_TOKEN = "132390383-rbLqsAZcE1DVuUUNH6hGIl3Tuh3yT851jXfTnkYd";
    private final static String ACCESS_TOKEN_SECRET = "DakYSNaK161U9J8lCtd19Ty5s9sCWcyIDvYLCWN2WCOuQ";

    private static Access instance = null;

    private Twitter twitter;

    //Constructor
    private Access() {
        twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);

        AccessToken oathAccessToken = new AccessToken(ACCESS_TOKEN,
                ACCESS_TOKEN_SECRET);

        twitter.setOAuthAccessToken(oathAccessToken);

    }

    public static Access getInstance() {
        if (instance == null) {
            instance = new Access();
        }
        return instance;
    }

    public Twitter getTwitterAccess() {
        return twitter;
    }
}
