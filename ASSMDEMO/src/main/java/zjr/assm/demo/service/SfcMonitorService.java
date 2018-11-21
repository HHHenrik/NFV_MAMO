package zjr.assm.demo.service;

import zjr.assm.demo.po.SfcMonitor;
import zjr.assm.demo.po.SfcMonitorCustom;

import java.util.List;

public interface SfcMonitorService {
    public List<Integer> getSfcIdList();
    public  List<SfcMonitor> getRecentSfcStatus();
    public SfcMonitorCustom getSfcInfoByName(String sfcName);
    public SfcMonitorCustom getSfcMonitorData(int sfcId);
    public void insertSfcData(SfcMonitorCustom sfcMonitorCustom);
}
