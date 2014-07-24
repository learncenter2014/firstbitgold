package bl.beans;

public class VmmInfoBean extends Bean {
    private String vmmip;
    private String vmmuser;
    private String vmmpass;
    private String comments;

    public String getVmmip() {
        return vmmip;
    }

    public void setVmmip(String vmmip) {
        this.vmmip = vmmip;
    }

    public String getVmmuser() {
        return vmmuser;
    }

    public void setVmmuser(String vmmuser) {
        this.vmmuser = vmmuser;
    }

    public String getVmmpass() {
        return vmmpass;
    }

    public void setVmmpass(String vmmpass) {
        this.vmmpass = vmmpass;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
