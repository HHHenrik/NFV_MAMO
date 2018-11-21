package zjr.assm.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.PhyNodeMonitorDao;
import zjr.assm.demo.po.PhyNodeMonitorCustom;
import zjr.assm.demo.service.PhyNodeMonitorService;

import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class PhyNodeMonitorServiceImpl implements PhyNodeMonitorService {
    @Autowired
    private PhyNodeMonitorDao phyNodeMonitorDao;

    public List<PhyNodeMonitorCustom> getPhyNodeMonitorData(HashMap map) {
        return phyNodeMonitorDao.getPhyNodeMonitorData(map);
    }

    public List<PhyNodeMonitorCustom> getNodeMonitorData() {
        return phyNodeMonitorDao.getNodeMonitorData();
    }

    public void insertNodeMonitorData(PhyNodeMonitorCustom phyNodeMonitorCustom) {
        phyNodeMonitorDao.insertNodeMonitorData(phyNodeMonitorCustom);
    }
}
