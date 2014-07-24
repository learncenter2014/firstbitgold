package bl.beans;

public class IpInfoBean extends Bean {
    private IpTagBean tag;
    private SubNetInfoBean subnetInfo;
    private String ip;
    private String comments;

    public IpTagBean getTag() {
        return tag;
    }

    public void setTag(IpTagBean tag) {
        this.tag = tag;
    }

    public SubNetInfoBean getSubnetInfo() {
        return subnetInfo;
    }

    public void setSubnetInfo(SubNetInfoBean subnetInfo) {
        this.subnetInfo = subnetInfo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
