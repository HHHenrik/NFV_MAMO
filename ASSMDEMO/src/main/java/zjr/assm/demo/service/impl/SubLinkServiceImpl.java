package zjr.assm.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.SubLinkDao;
import zjr.assm.demo.po.SubLink;
import zjr.assm.demo.po.SubLinkCustom;
import zjr.assm.demo.service.SubLinkService;

import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class SubLinkServiceImpl implements SubLinkService {
    @Autowired
    private SubLinkDao subLinkDao;

    public List<SubLink> getTopo() {
        return subLinkDao.getTopo();
    }

    public SubLinkCustom getLink(HashMap map) {
        return subLinkDao.getLink(map);
    }
}
