package zjr.assm.demo.dao;

import org.springframework.stereotype.Repository;
import zjr.assm.demo.po.PhyNodeMonitorCustom;

import java.util.HashMap;
import java.util.List;

@Repository
public interface PhyNodeMonitorDao {
    List<PhyNodeMonitorCustom> getPhyNodeMonitorData(HashMap map);
    List<PhyNodeMonitorCustom> getNodeMonitorData();
    void insertNodeMonitorData(PhyNodeMonitorCustom phyNodeMonitorCustom);
}
