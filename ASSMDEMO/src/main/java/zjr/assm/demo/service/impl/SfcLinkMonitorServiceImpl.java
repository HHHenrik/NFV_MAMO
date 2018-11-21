package zjr.assm.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.SfcLinkMonitorDao;
import zjr.assm.demo.po.SfcLinkMonitor;
import zjr.assm.demo.po.SfcLinkMonitorCustom;
import zjr.assm.demo.service.SfcLinkMonitorService;

import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class SfcLinkMonitorServiceImpl implements SfcLinkMonitorService {
    @Autowired
    private SfcLinkMonitorDao sfcLinkMonitorDao;

    public List<SfcLinkMonitor> getSfcLinkList(int sfcId) {
        return sfcLinkMonitorDao.getSfcLinkList(sfcId);
    }

    public List<SfcLinkMonitorCustom> getSfcLinkMonitorData(String sfcName) {
        return sfcLinkMonitorDao.getSfcLinkMonitorData(sfcName);
    }

    public List<SfcLinkMonitorCustom> getLinkMonitorData(HashMap map) {
        return sfcLinkMonitorDao.getLinkMonitorData(map);
    }

    public void insertSfcLinkData(SfcLinkMonitorCustom sfcLinkMonitorCustom) {
        sfcLinkMonitorDao.insertSfcLinkData(sfcLinkMonitorCustom);
    }
}
