package zjr.assm.demo.po;

public class SubLink {
    private String from;
    private String to;
    private String fromDpId;
    private String toDpId;
    private int bandwidth;
    private int delay;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFromDpId() {
        return fromDpId;
    }

    public void setFromDpId(String fromDpId) {
        this.fromDpId = fromDpId;
    }

    public String getToDpId() {
        return toDpId;
    }

    public void setToDpId(String toDpId) {
        this.toDpId = toDpId;
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
}
