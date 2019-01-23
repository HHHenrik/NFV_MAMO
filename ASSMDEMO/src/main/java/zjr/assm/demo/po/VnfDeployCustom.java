package zjr.assm.demo.po;

import java.util.List;

public class VnfDeployCustom extends VnfDeploy {
    private List<VnfDeploy> vnfResList;   //vnf 映射在物理网络上的对应关系

    public List<VnfDeploy> getVnfResList() {
        return vnfResList;
    }

    public void setVnfResList(List<VnfDeploy> vnfResList) {
        this.vnfResList = vnfResList;
    }
}
