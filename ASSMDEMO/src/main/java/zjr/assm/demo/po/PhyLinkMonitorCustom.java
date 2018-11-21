package zjr.assm.demo.po;

import java.util.List;

public class PhyLinkMonitorCustom extends PhyLinkMonitor {
    private List<PhyLinkMonitor> phyLinkMonitorList;

    public List<PhyLinkMonitor> getPhyLinkMonitorList() {
        return phyLinkMonitorList;
    }

    public void setPhyLinkMonitorList(List<PhyLinkMonitor> phyLinkMonitorList) {
        this.phyLinkMonitorList = phyLinkMonitorList;
    }
}