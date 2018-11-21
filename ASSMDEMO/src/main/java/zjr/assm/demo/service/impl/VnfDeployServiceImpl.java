package zjr.assm.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.VnfDeployDao;
import zjr.assm.demo.po.VnfDeploy;
import zjr.assm.demo.po.VnfDeployCustom;
import zjr.assm.demo.service.VnfDeployService;

import java.util.List;

@Transactional
@Service
public class VnfDeployServiceImpl implements VnfDeployService {
    @Autowired
    private VnfDeployDao vnfDeployDao;

    public void insertBactch(VnfDeployCustom vnfDeployCustom) {
        vnfDeployDao.insertBactch(vnfDeployCustom);
    }

    public List<VnfDeploy> selectResBySfcId(int sfcId) {
        return vnfDeployDao.selectResBySfcId(sfcId);
    }

    public void deleteDataById(int sfcId) {
        vnfDeployDao.deleteDataById(sfcId);
    }
}
