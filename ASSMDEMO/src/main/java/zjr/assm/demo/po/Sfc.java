package zjr.assm.demo.po;

import java.util.Date;

public class Sfc {
    private int sfcId;
    private String sfcName;
    private String userName;
    private String status;
    private Date createTime;

    public int getSfcId() {
        return sfcId;
    }

    public void setSfcId(int sfcId) {
        this.sfcId = sfcId;
    }

    public String getSfcName() {
        return sfcName;
    }

    public void setSfcName(String sfcName) {
        this.sfcName = sfcName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
