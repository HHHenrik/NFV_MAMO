package zjr.assm.demo.po;

public class SfcNode {
    private int sfcId;
    private int vnfdId;
    private String vnfd;
    private String vnfName;

    public int getSfcId() {
        return sfcId;
    }

    public void setSfcId(int sfcId) {
        this.sfcId = sfcId;
    }

    public int getVnfdId() {
        return vnfdId;
    }

    public void setVnfdId(int vnfdId) {
        this.vnfdId = vnfdId;
    }

    public String getVnfd() {
        return vnfd;
    }

    public void setVnfd(String vnfd) {
        this.vnfd = vnfd;
    }

    public String getVnfName() {
        return vnfName;
    }

    public void setVnfName(String vnfName) {
        this.vnfName = vnfName;
    }
}
