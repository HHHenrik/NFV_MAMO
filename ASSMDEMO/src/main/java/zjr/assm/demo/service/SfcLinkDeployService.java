package zjr.assm.demo.service;

import zjr.assm.demo.po.SfcLinkDeploy;
import zjr.assm.demo.po.SfcLinkDeployCustom;

import java.util.List;

public interface SfcLinkDeployService {
    public List<SfcLinkDeploy> selectLinkById(int sfcId);
    public void deleteDataById(int sfcId);
    public void insertLinkResBatch(SfcLinkDeployCustom sfcLinkDeployCustom);
}
