package zjr.assm.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.VnfdDao;
import zjr.assm.demo.po.Vnfd;
import zjr.assm.demo.po.VnfdCustom;
import zjr.assm.demo.service.VnfdService;

import java.util.List;

@Transactional
@Service
public class VnfdServiceImpl implements VnfdService {
    @Autowired
    private VnfdDao vnfdDao;

    public List<Vnfd> getVnfdList() {
        return vnfdDao.getVnfdList();
    }

    public void deleteVnfd(Vnfd vnfd) {
        vnfdDao.deleteVnfd(vnfd);
    }

    public void deleteVnfdBatch(VnfdCustom vnfdCustom) {
        vnfdDao.deleteVnfdBatch(vnfdCustom);
    }

    public void addVnfd(Vnfd vnfd) {vnfdDao.addVnfd(vnfd);}

    public Vnfd findVnfByVnfd(Vnfd vnfd) {
        return vnfdDao.findVnfByVnfd(vnfd);
    }

    public int hasInvalidVnf(VnfdCustom vnfdCustom) {
        return vnfdDao.hasInvalidVnf(vnfdCustom);
    }
}
