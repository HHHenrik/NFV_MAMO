package zjr.assm.demo.dao;

import org.springframework.stereotype.Repository;
import zjr.assm.demo.po.PhyLinkMonitorCustom;

import java.util.HashMap;
import java.util.List;

@Repository
public interface PhyLinkMonitorDao {
    List<PhyLinkMonitorCustom> getLinkMonitorData();
    List<PhyLinkMonitorCustom> getPhyLinkMonitorData(HashMap map);
    void insertLinkMonitorData(PhyLinkMonitorCustom phyLinkMonitorCustom);
}
