package zjr.assm.demo.dao;

import org.springframework.stereotype.Repository;
import zjr.assm.demo.po.SfcLink;
import zjr.assm.demo.po.SfcLinkCustom;

import java.util.HashMap;
import java.util.List;

@Repository
public interface SfcLinkDao {
    List<SfcLink> selectLinkById(int sfcId);
    void insertLinkBatch(SfcLinkCustom sfcLinkCustom);
    int getLinkNum(int sfcId);
    SfcLinkCustom getLinkById(HashMap map);
}
