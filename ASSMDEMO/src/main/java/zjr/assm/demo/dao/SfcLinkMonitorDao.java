package zjr.assm.demo.dao;

import org.springframework.stereotype.Repository;
import zjr.assm.demo.po.SfcLinkMonitor;
import zjr.assm.demo.po.SfcLinkMonitorCustom;

import java.util.HashMap;
import java.util.List;

@Repository
public interface SfcLinkMonitorDao {
    List<SfcLinkMonitor> getSfcLinkList(int sfcId);
    List<SfcLinkMonitorCustom> getSfcLinkMonitorData(String sfcName);
    List<SfcLinkMonitorCustom> getLinkMonitorData(HashMap map);
    void insertSfcLinkData(SfcLinkMonitorCustom sfcLinkMonitorCustom);
}
