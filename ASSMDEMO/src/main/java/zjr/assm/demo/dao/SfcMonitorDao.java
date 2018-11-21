package zjr.assm.demo.dao;

import org.springframework.stereotype.Repository;
import zjr.assm.demo.po.SfcMonitor;
import zjr.assm.demo.po.SfcMonitorCustom;

import java.util.List;

@Repository
public interface SfcMonitorDao {
    List<Integer> getSfcIdList();
    List<SfcMonitor> getRecentSfcStatus();
    SfcMonitorCustom getSfcInfoByName(String sfcName);
    SfcMonitorCustom getSfcMonitorData(int sfcId);
    void insertSfcData(SfcMonitorCustom sfcMonitorCustom);
}
