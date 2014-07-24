package bl.beans;

public class SubNetInfoBean extends Bean {
    private String subnet;
    private SubNetBean subtype;
    private String gateway;
    private String board;
    private String netmask;
    private int ipcount;
    private String vlan;
    private int addrtype;
    private String comments;

    public String getSubnet() {
        return subnet;
    }

    public void setSubnet(String subnet) {
        this.subnet = subnet;
    }

    public SubNetBean getSubtype() {
        return subtype;
    }

    public void setSubtype(SubNetBean subtype) {
        this.subtype = subtype;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public int getIpcount() {
        return ipcount;
    }

    public void setIpcount(int ipcount) {
        this.ipcount = ipcount;
    }

    public String getVlan() {
        return vlan;
    }

    public void setVlan(String vlan) {
        this.vlan = vlan;
    }

    public int getAddrtype() {
        return addrtype;
    }

    public void setAddrtype(int addrtype) {
        this.addrtype = addrtype;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "<td>" + subtype + "</td><td>" + gateway + "</td><td>" + board + "</td><td>" + netmask + "</td><td>" + ipcount + "</td><td>" + vlan
                + "</td><td>" + addrtype + "</td><td>" + comments + "</td>";
    }

}
