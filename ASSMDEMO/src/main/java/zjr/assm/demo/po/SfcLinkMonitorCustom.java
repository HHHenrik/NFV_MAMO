package zjr.assm.demo.po;

import java.util.List;

public class SfcLinkMonitorCustom extends SfcLinkMonitor {
    private List<SfcLinkMonitor> sfcLinkMonitorList;

    public List<SfcLinkMonitor> getSfcLinkMonitorList() {
        return sfcLinkMonitorList;
    }

    public void setSfcLinkMonitorList(List<SfcLinkMonitor> sfcLinkMonitorList) {
        this.sfcLinkMonitorList = sfcLinkMonitorList;
    }
}
