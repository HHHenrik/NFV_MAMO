package zjr.assm.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.VnfdRelevanceDao;
import zjr.assm.demo.po.VnfdRelevance;
import zjr.assm.demo.service.VnfdRelevanceService;

@Transactional
@Service
public class VnfdRelevanceServiceImpl implements VnfdRelevanceService {
    @Autowired
    private VnfdRelevanceDao vnfdRelevanceDao;

    public VnfdRelevance findSpecifyVnf(String vnfd) {
        return vnfdRelevanceDao.findSpecifyVnf(vnfd);
    }

    public void updateVnfNum(VnfdRelevance vnfdRelevance){
        vnfdRelevanceDao.updateVnfNum(vnfdRelevance);
    }

    public void deleteVnfByVnfd(String vnfd) {
        vnfdRelevanceDao.deleteVnfByVnfd(vnfd);
    }

    public int hasMatchRecord(String vnfd) {
        return vnfdRelevanceDao.hasMatchRecord(vnfd);
    }

    public void insertVnfdRelevance(VnfdRelevance vnfdRelevance) {
        vnfdRelevanceDao.insertVnfdRelevance(vnfdRelevance);
    }
}
