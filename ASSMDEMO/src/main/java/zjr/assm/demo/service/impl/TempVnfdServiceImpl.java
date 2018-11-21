package zjr.assm.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.TempVnfdDao;
import zjr.assm.demo.po.Vnfd;
import zjr.assm.demo.service.TempVnfdService;

@Transactional
@Service
public class TempVnfdServiceImpl implements TempVnfdService {
    @Autowired
    private TempVnfdDao tempVnfdDao;

    public void addTempVnfd(Vnfd vnfd) {
        tempVnfdDao.addTempVnfd(vnfd);
    }

    public void deleteVnfByVnfd(String vnfd) {
        tempVnfdDao.deleteVnfByVnfd(vnfd);
    }

    public String findSwPath(String vnfd) {
        return tempVnfdDao.findSwPath(vnfd);
    }
}
