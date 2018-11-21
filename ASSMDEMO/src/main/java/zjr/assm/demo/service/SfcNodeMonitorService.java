package zjr.assm.demo.service;

import zjr.assm.demo.po.SfcNodeMonitor;
import zjr.assm.demo.po.SfcNodeMonitorCustom;

import java.util.HashMap;
import java.util.List;

public interface SfcNodeMonitorService {
    public List<SfcNodeMonitor> getSfcNodeList(int sfcId);
    public List<SfcNodeMonitorCustom> getSfcNodeMonitorData(String sfcName);
    public List<SfcNodeMonitorCustom> getNodeMonitorData(HashMap map);
    public void insertSfcNodeData(SfcNodeMonitorCustom sfcNodeMonitorCustom);
}
