package project.entity;

import java.util.Date;

/**
 * Created by Stephen on 1/17/2015.
 */
public class UserTweet {
    private Long entryId;
    private Long userId;
    private String tweet;
    private Date tweetDate;

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public Date getTweetDate() {
        return tweetDate;
    }

    public void setTweetDate(Date tweetDate) {
        this.tweetDate = tweetDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTweet userTweet = (UserTweet) o;

        if (entryId != null ? !entryId.equals(userTweet.entryId) : userTweet.entryId != null) return false;
        if (tweet != null ? !tweet.equals(userTweet.tweet) : userTweet.tweet != null) return false;
        if (tweetDate != null ? !tweetDate.equals(userTweet.tweetDate) : userTweet.tweetDate != null) return false;
        if (userId != null ? !userId.equals(userTweet.userId) : userTweet.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = entryId != null ? entryId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (tweet != null ? tweet.hashCode() : 0);
        result = 31 * result + (tweetDate != null ? tweetDate.hashCode() : 0);
        return result;
    }
}
