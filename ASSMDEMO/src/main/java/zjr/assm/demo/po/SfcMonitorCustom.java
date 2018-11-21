package zjr.assm.demo.po;

import java.util.List;

public class SfcMonitorCustom extends SfcMonitor {
    private String userName;
    private String sfcName;
    private List<SfcMonitor> sfcMonitorList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSfcName() {
        return sfcName;
    }

    public void setSfcName(String sfcName) {
        this.sfcName = sfcName;
    }

    public List<SfcMonitor> getSfcMonitorList() {
        return sfcMonitorList;
    }

    public void setSfcMonitorList(List<SfcMonitor> sfcMonitorList) {
        this.sfcMonitorList = sfcMonitorList;
    }
}
