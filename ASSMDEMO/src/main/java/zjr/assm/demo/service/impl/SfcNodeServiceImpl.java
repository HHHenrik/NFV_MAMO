package zjr.assm.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.SfcNodeDao;
import zjr.assm.demo.po.SfcNode;
import zjr.assm.demo.po.SfcNodeCustom;
import zjr.assm.demo.po.Vnf;
import zjr.assm.demo.service.SfcNodeService;

import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class SfcNodeServiceImpl implements SfcNodeService {
    @Autowired
    private SfcNodeDao sfcNodeDao;

    public List<SfcNodeCustom> finSfcNodeById(int sfcId) {
        return sfcNodeDao.finSfcNodeById(sfcId);
    }

    public List<SfcNodeCustom> getSfcNode(int sfcId) {
        return sfcNodeDao.getSfcNode(sfcId);
    }

    public int getVnfSum(int sfcId) {
        return sfcNodeDao.getVnfSum(sfcId);
    }

    public void insertNodeBatch(SfcNodeCustom sfcNodeCustom) {
        sfcNodeDao.insertNodeBatch(sfcNodeCustom);
    }

    public List<SfcNode> getSfcNodeById(int sfcId) {
        return sfcNodeDao.getSfcNodeById(sfcId);
    }

    public Vnf selVnfByVnfName(SfcNodeCustom sfcNodeCustom) {
        return sfcNodeDao.selVnfByVnfName(sfcNodeCustom);
    }

    public List<SfcNodeCustom> getVnfNodeById(HashMap map) {
        return sfcNodeDao.getVnfNodeById(map);
    }
}
