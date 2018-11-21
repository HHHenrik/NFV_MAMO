package zjr.assm.demo.po;

public class VnfDeploy {
    private String vnfd;
    private int vnfId;
    private int sfcId;
    private String nodeId;

    public String getVnfd() {
        return vnfd;
    }

    public void setVnfd(String vnfd) {
        this.vnfd = vnfd;
    }

    public int getVnfId() {
        return vnfId;
    }

    public void setVnfId(int vnfId) {
        this.vnfId = vnfId;
    }

    public int getSfcId() {
        return sfcId;
    }

    public void setSfcId(int sfcId) {
        this.sfcId = sfcId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}
