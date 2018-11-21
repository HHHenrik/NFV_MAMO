package zjr.assm.demo.service;

import zjr.assm.demo.po.PhyLinkMonitorCustom;

import java.util.HashMap;
import java.util.List;

public interface PhyLinkMonitorService {
    List<PhyLinkMonitorCustom> getLinkMonitorData();
    List<PhyLinkMonitorCustom> getPhyLinkMonitorData(HashMap map);
    void insertLinkMonitorData(PhyLinkMonitorCustom phyLinkMonitorCustom);
}
