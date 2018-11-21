package zjr.assm.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.SfcLinkDeployDao;
import zjr.assm.demo.po.SfcLinkDeploy;
import zjr.assm.demo.po.SfcLinkDeployCustom;
import zjr.assm.demo.service.SfcLinkDeployService;

import java.util.List;

@Transactional
@Service
public class SfcLinkDeployServiceImpl implements SfcLinkDeployService {
    @Autowired
    private SfcLinkDeployDao sfcLinkDeployDao;
    public List<SfcLinkDeploy> selectLinkById(int sfcId) {
        return sfcLinkDeployDao.selectLinkById(sfcId);
    }

    public void deleteDataById(int sfcId) {
        sfcLinkDeployDao.deleteDataById(sfcId);
    }

    public void insertLinkResBatch(SfcLinkDeployCustom sfcLinkDeployCustom) {
        sfcLinkDeployDao.insertLinkResBatch(sfcLinkDeployCustom);
    }
}
