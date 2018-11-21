package zjr.assm.demo.service;

import zjr.assm.demo.po.VnfDeploy;
import zjr.assm.demo.po.VnfDeployCustom;

import java.util.List;

public interface VnfDeployService {
    public void insertBactch(VnfDeployCustom vnfDeployCustom);
    public List<VnfDeploy> selectResBySfcId(int sfcId);
    public void deleteDataById(int sfcId);
}
