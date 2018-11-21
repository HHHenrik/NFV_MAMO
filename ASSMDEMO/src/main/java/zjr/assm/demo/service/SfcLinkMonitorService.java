package zjr.assm.demo.service;

import zjr.assm.demo.po.SfcLinkMonitor;
import zjr.assm.demo.po.SfcLinkMonitorCustom;

import java.util.HashMap;
import java.util.List;

public interface SfcLinkMonitorService {
    public List<SfcLinkMonitor> getSfcLinkList(int sfcId);
    public List<SfcLinkMonitorCustom> getSfcLinkMonitorData(String sfcName);
    public List<SfcLinkMonitorCustom> getLinkMonitorData(HashMap map);
    public void insertSfcLinkData(SfcLinkMonitorCustom sfcLinkMonitorCustom);
}
