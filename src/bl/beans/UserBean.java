package bl.beans;

/**
 * Created by pli on 14-7-14.
 */

public class UserBean extends Bean {
    private int ugroup;
    private String Location;
    private String Comments;
    private String password;
    private String email;


    public int getUgroup() {
        return ugroup;
    }

    public void setUgroup(int ugroup) {
        this.ugroup = ugroup;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
