package zjr.assm.demo.po;

import java.util.List;

public class VnfDeployCustom extends VnfDeploy {
    private List<VnfDeploy> vnfResList;

    public List<VnfDeploy> getVnfResList() {
        return vnfResList;
    }

    public void setVnfResList(List<VnfDeploy> vnfResList) {
        this.vnfResList = vnfResList;
    }
}
