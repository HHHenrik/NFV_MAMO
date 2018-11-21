package zjr.assm.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.SfcLinkDao;
import zjr.assm.demo.po.SfcLink;
import zjr.assm.demo.po.SfcLinkCustom;
import zjr.assm.demo.service.SfcLinkService;

import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class SfcLinkServiceImpl implements SfcLinkService {
    @Autowired
    private SfcLinkDao sfcLinkDao;

    public List<SfcLink> selectLinkById(int sfcId) {
        return sfcLinkDao.selectLinkById(sfcId);
    }

    public void insertLinkBatch(SfcLinkCustom sfcLinkCustom) {
        sfcLinkDao.insertLinkBatch(sfcLinkCustom);
    }

    public int getLinkNum(int sfcId) {
        return sfcLinkDao.getLinkNum(sfcId);
    }

    public SfcLinkCustom getLinkById(HashMap map) {
        return sfcLinkDao.getLinkById(map);
    }
}
