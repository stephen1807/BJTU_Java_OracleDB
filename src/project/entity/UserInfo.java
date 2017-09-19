package project.entity;

import java.util.Date;

/**
 * Created by Stephen on 1/17/2015.
 */
public class UserInfo {
    private Long userId;
    private Date lastupdate;

    public UserInfo() {
    }

    public UserInfo(Long id, Date date) {
        this.userId = id;
        this.lastupdate = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (userId != userInfo.userId) return false;
        if (lastupdate != null ? !lastupdate.equals(userInfo.lastupdate) : userInfo.lastupdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (lastupdate != null ? lastupdate.hashCode() : 0);
        return result;
    }
}
