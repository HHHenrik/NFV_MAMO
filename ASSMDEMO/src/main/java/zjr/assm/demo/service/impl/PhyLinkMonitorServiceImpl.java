package zjr.assm.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.PhyLinkMonitorDao;
import zjr.assm.demo.po.PhyLinkMonitorCustom;
import zjr.assm.demo.service.PhyLinkMonitorService;

import java.util.HashMap;
import java.util.List;

@Transactional
@Controller
public class PhyLinkMonitorServiceImpl implements PhyLinkMonitorService {
    @Autowired
    private PhyLinkMonitorDao phyLinkMonitorDao;

    public List<PhyLinkMonitorCustom> getLinkMonitorData() {
        return phyLinkMonitorDao.getLinkMonitorData();
    }

    public List<PhyLinkMonitorCustom> getPhyLinkMonitorData(HashMap map) {
        return phyLinkMonitorDao.getPhyLinkMonitorData(map);
    }

    public void insertLinkMonitorData(PhyLinkMonitorCustom phyLinkMonitorCustom) {
        phyLinkMonitorDao.insertLinkMonitorData(phyLinkMonitorCustom);
    }
}
