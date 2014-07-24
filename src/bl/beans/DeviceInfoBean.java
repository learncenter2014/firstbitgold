package bl.beans;

import java.util.Set;

public class DeviceInfoBean extends Bean {
    private DeviceTypeBean devtype;
    private String devtypeinfo;
    private int devmodel;
    private int poolid;
    private int rack;
    private int slot;
    private int userid;
    private int project;
    private String comments;

    private Set<IpInfoBean> ipInfos;

    public String getDevtypeinfo() {
        this.devtypeinfo = this.devtype.getName();
        return devtypeinfo;
    }

    public Set<IpInfoBean> getIpInfos() {
        return ipInfos;
    }

    public void setIpInfos(Set<IpInfoBean> ipInfos) {
        this.ipInfos = ipInfos;
    }

    public DeviceTypeBean getDevtype() {
        return devtype;
    }

    public void setDevtype(DeviceTypeBean devtype) {
        this.devtype = devtype;
    }

    public int getDevmodel() {
        return devmodel;
    }

    public void setDevmodel(int devmodel) {
        this.devmodel = devmodel;
    }

    public int getPoolid() {
        return poolid;
    }

    public void setPoolid(int poolid) {
        this.poolid = poolid;
    }

    public int getRack() {
        return rack;
    }

    public void setRack(int rack) {
        this.rack = rack;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getIpInfoDisplay() {
        Set<IpInfoBean> ips = this.getIpInfos();
        StringBuilder sbuilder = new StringBuilder();
        if (ips != null) {
            sbuilder.append("<table border=\"1\">");
            sbuilder.append("<th>ip</th><th>tag</th><th>subtype</th><th>gateway</th><th>board</th><th>netmask</th><th>ipcount</th><th>vlan</th><th>addrtype</th><th>comments</th>");
            for (IpInfoBean ip : ips) {
                sbuilder.append("<tr>");
                sbuilder.append("<td>" + ip.getIp() + "</td>");
                sbuilder.append("<td>" + ip.getTag() + "</td>");
                sbuilder.append(ip.getSubnetInfo());
                sbuilder.append("</tr>");
            }
            sbuilder.append("</table>");
        }
        return sbuilder.toString();
    }
}
