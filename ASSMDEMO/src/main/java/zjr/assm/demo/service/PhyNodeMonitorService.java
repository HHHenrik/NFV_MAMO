package zjr.assm.demo.service;

import zjr.assm.demo.po.PhyNodeMonitorCustom;

import java.util.HashMap;
import java.util.List;

public interface PhyNodeMonitorService {
    public List<PhyNodeMonitorCustom> getPhyNodeMonitorData(HashMap map);
    public List<PhyNodeMonitorCustom> getNodeMonitorData();
    public void insertNodeMonitorData(PhyNodeMonitorCustom phyNodeMonitorCustom);
}
