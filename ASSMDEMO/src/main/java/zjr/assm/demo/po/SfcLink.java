package zjr.assm.demo.po;

public class SfcLink {
    private int sfcId;
    private int from;
    private int to;
    private int bandwidth;
    private int delay;
    private int flag;
    private String fromVnf;
    private String toVnf;
    private int linkId;

    public int getSfcId() {
        return sfcId;
    }

    public void setSfcId(int sfcId) {
        this.sfcId = sfcId;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(int bandwidth) {
        this.bandwidth = bandwidth;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getFromVnf() {
        return fromVnf;
    }

    public void setFromVnf(String fromVnf) {
        this.fromVnf = fromVnf;
    }

    public String getToVnf() {
        return toVnf;
    }

    public void setToVnf(String toVnf) {
        this.toVnf = toVnf;
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }
}
