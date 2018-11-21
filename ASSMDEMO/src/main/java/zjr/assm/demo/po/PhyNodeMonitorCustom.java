package zjr.assm.demo.po;

import java.util.List;

public class PhyNodeMonitorCustom extends PhyNodeMonitor {
    private List<PhyNodeMonitor> phyNodeMonitorList;

    public List<PhyNodeMonitor> getPhyNodeMonitorList() {
        return phyNodeMonitorList;
    }

    public void setPhyNodeMonitorList(List<PhyNodeMonitor> phyNodeMonitorList) {
        this.phyNodeMonitorList = phyNodeMonitorList;
    }
}
