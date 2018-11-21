package zjr.assm.demo.po;

import java.util.Date;

public class SfcMonitor {
    private int sfcId;
    private Date currentTime;
    private float packageReceive;
    private float packageLoss;
    private float packageDeal;
    private float throughput;
    private int alarmLevel;

    public int getSfcId() {
        return sfcId;
    }

    public void setSfcId(int sfcId) {
        this.sfcId = sfcId;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public float getPackageReceive() {
        return packageReceive;
    }

    public void setPackageReceive(float packageReceive) {
        this.packageReceive = packageReceive;
    }

    public float getPackageLoss() {
        return packageLoss;
    }

    public void setPackageLoss(float packageLoss) {
        this.packageLoss = packageLoss;
    }

    public float getThroughput() {
        return throughput;
    }

    public void setThroughput(float throughput) {
        this.throughput = throughput;
    }

    public int getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(int alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public float getPackageDeal() {
        return packageDeal;
    }

    public void setPackageDeal(float packageDeal) {
        this.packageDeal = packageDeal;
    }
}
