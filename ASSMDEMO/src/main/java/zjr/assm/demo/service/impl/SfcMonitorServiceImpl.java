package zjr.assm.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.SfcMonitorDao;
import zjr.assm.demo.po.SfcMonitor;
import zjr.assm.demo.po.SfcMonitorCustom;
import zjr.assm.demo.service.SfcMonitorService;

import java.util.List;

@Transactional
@Service
public class SfcMonitorServiceImpl implements SfcMonitorService {
    @Autowired
    private SfcMonitorDao sfcMonitorDao;

    public List<Integer> getSfcIdList() {
        return sfcMonitorDao.getSfcIdList();
    }

    public List<SfcMonitor> getRecentSfcStatus() {
        return sfcMonitorDao.getRecentSfcStatus();
    }

    public SfcMonitorCustom getSfcInfoByName(String sfcName) {
        return sfcMonitorDao.getSfcInfoByName(sfcName);
    }

    public SfcMonitorCustom getSfcMonitorData(int sfcId) {
        return sfcMonitorDao.getSfcMonitorData(sfcId);
    }

    public void insertSfcData(SfcMonitorCustom sfcMonitorCustom) {
        sfcMonitorDao.insertSfcData(sfcMonitorCustom);
    }
}
