package zjr.assm.demo.po;

import java.util.Date;

public class SfcLinkMonitor {
    private int sfcId;
    private float bwUtilRate;
    private float delay;
    private float bwThresholdDown;
    private float bwThresholdUp;
    private float delayThreshold;
    private int alarmLevel;
    private Date currentTime;
    private int linkId;

    public int getSfcId() {
        return sfcId;
    }

    public void setSfcId(int sfcId) {
        this.sfcId = sfcId;
    }

    public float getBwUtilRate() {
        return bwUtilRate;
    }

    public void setBwUtilRate(float bwUtilRate) {
        this.bwUtilRate = bwUtilRate;
    }

    public float getDelay() {
        return delay;
    }

    public void setDelay(float delay) {
        this.delay = delay;
    }

    public float getDelayThreshold() {
        return delayThreshold;
    }

    public void setDelayThreshold(float delayThreshold) {
        this.delayThreshold = delayThreshold;
    }

    public int getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(int alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public float getBwThresholdDown() {
        return bwThresholdDown;
    }

    public void setBwThresholdDown(float bwThresholdDown) {
        this.bwThresholdDown = bwThresholdDown;
    }

    public float getBwThresholdUp() {
        return bwThresholdUp;
    }

    public void setBwThresholdUp(float bwThresholdUp) {
        this.bwThresholdUp = bwThresholdUp;
    }
}
