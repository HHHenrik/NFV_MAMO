package zjr.assm.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.SfcDao;
import zjr.assm.demo.po.Sfc;
import zjr.assm.demo.po.SfcCustom;
import zjr.assm.demo.service.SfcService;

import java.util.List;

@Transactional
@Service
public class SfcServiceImpl implements SfcService{
    @Autowired
    private SfcDao sfcDao;
    public void addSfc(Sfc sfc) {
        sfcDao.addSfc(sfc);
    }

    public List<Sfc> getSfcList(String userName) {
        return sfcDao.getSfcList(userName);
    }

    public void deleteSfc(List<Integer> sfcIdList) {
        sfcDao.deleteSfc(sfcIdList);
    }

    public void updateSfcStatus(Sfc sfc) {
        sfcDao.updateSfcStatus(sfc);
    }

    public SfcCustom getSfcById(int sfcId) {
        return sfcDao.getSfcById(sfcId);
    }

    public String getSfcStatus(int sfcId) {
        return sfcDao.getSfcStatus(sfcId);
    }
}
