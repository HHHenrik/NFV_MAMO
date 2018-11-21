package zjr.assm.demo.service;

import zjr.assm.demo.po.SfcLink;
import zjr.assm.demo.po.SfcLinkCustom;

import java.util.HashMap;
import java.util.List;

public interface SfcLinkService {
    public List<SfcLink> selectLinkById(int sfcId);
    public void insertLinkBatch(SfcLinkCustom sfcLinkCustom);
    public int getLinkNum(int sfcId);
    public SfcLinkCustom getLinkById(HashMap map);
}
