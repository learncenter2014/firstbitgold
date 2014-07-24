package bl.beans;

public class DashBoardBean {
    private long userCounter;
    private long projectCounter;
    private long deviceCounter;

    public long getUserCounter() {
        return userCounter;
    }

    public void setUserCounter(long userCounter) {
        this.userCounter = userCounter;
    }

    public long getProjectCounter() {
        return projectCounter;
    }

    public void setProjectCounter(long projectCounter) {
        this.projectCounter = projectCounter;
    }

    public long getDeviceCounter() {
        return deviceCounter;
    }

    public void setDeviceCounter(long deviceCounter) {
        this.deviceCounter = deviceCounter;
    }
}
