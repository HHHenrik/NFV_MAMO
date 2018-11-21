package zjr.assm.demo.dao;

import org.springframework.stereotype.Repository;
import zjr.assm.demo.po.SfcNodeMonitor;
import zjr.assm.demo.po.SfcNodeMonitorCustom;

import java.util.HashMap;
import java.util.List;

@Repository
public interface SfcNodeMonitorDao {
    List<SfcNodeMonitor> getSfcNodeList(int sfcId);
    List<SfcNodeMonitorCustom> getSfcNodeMonitorData(String sfcName);
    List<SfcNodeMonitorCustom> getNodeMonitorData(HashMap map);
    void insertSfcNodeData(SfcNodeMonitorCustom sfcNodeMonitorCustom);
}
