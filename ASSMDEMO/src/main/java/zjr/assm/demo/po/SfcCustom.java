package zjr.assm.demo.po;

import java.util.Date;
import java.util.List;

public class SfcCustom extends Sfc {
    private float packageReceive;
    private float packageDeal;
    private float packageLoss;
    private int alarmLevel;
    private float throughput;
    private Date currentTime;
    private List<Integer> sfcIdList;

    public List<Integer> getSfcIdList() {
        return sfcIdList;
    }

    public void setSfcIdList(List<Integer> sfcIdList) {
        this.sfcIdList = sfcIdList;
    }

    public float getPackageReceive() {
        return packageReceive;
    }

    public void setPackageReceive(float packageReceive) {
        this.packageReceive = packageReceive;
    }

    public float getPackageDeal() {
        return packageDeal;
    }

    public void setPackageDeal(float packageDeal) {
        this.packageDeal = packageDeal;
    }

    public float getPackageLoss() {
        return packageLoss;
    }

    public void setPackageLoss(float packageLoss) {
        this.packageLoss = packageLoss;
    }

    public int getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(int alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public float getThroughput() {
        return throughput;
    }

    public void setThroughput(float throughput) {
        this.throughput = throughput;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }
}
