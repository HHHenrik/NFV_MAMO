package zjr.assm.demo.po;

import java.util.List;

public class SfcLinkDeployCustom extends SfcLinkDeploy {
    private List<SfcLinkDeploy> sfcLinkDeploys;

    public List<SfcLinkDeploy> getSfcLinkDeploys() {
        return sfcLinkDeploys;
    }

    public void setSfcLinkDeploys(List<SfcLinkDeploy> sfcLinkDeploys) {
        this.sfcLinkDeploys = sfcLinkDeploys;
    }
}
