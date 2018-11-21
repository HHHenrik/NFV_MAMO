package zjr.assm.demo.po;

import java.util.List;

public class SfcNodeMonitorCustom extends SfcNodeMonitor {
    private String sfcName;
    private String userName;
    private List<SfcNodeMonitor> sfcNodeMonitorList;

    public String getSfcName() {
        return sfcName;
    }

    public void setSfcName(String sfcName) {
        this.sfcName = sfcName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<SfcNodeMonitor> getSfcNodeMonitorList() {
        return sfcNodeMonitorList;
    }

    public void setSfcNodeMonitorList(List<SfcNodeMonitor> sfcNodeMonitorList) {
        this.sfcNodeMonitorList = sfcNodeMonitorList;
    }
}
