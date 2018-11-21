package zjr.assm.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.SfcNodeMonitorDao;
import zjr.assm.demo.po.SfcNodeMonitor;
import zjr.assm.demo.po.SfcNodeMonitorCustom;
import zjr.assm.demo.service.SfcNodeMonitorService;

import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class SfcNodeMonitorServiceImpl implements SfcNodeMonitorService {
    @Autowired
    private SfcNodeMonitorDao sfcNodeMonitorDao;

    public List<SfcNodeMonitor> getSfcNodeList(int sfcId) {
        return sfcNodeMonitorDao.getSfcNodeList(sfcId);
    }

    public List<SfcNodeMonitorCustom> getSfcNodeMonitorData(String sfcName) {
        return sfcNodeMonitorDao.getSfcNodeMonitorData(sfcName);
    }

    public List<SfcNodeMonitorCustom> getNodeMonitorData(HashMap map) {
        return sfcNodeMonitorDao.getNodeMonitorData(map);
    }

    public void insertSfcNodeData(SfcNodeMonitorCustom sfcNodeMonitorCustom) {
        sfcNodeMonitorDao.insertSfcNodeData(sfcNodeMonitorCustom);
    }
}
