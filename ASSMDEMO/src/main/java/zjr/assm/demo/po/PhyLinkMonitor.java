package zjr.assm.demo.po;

import java.util.Date;

public class PhyLinkMonitor {
    private String from;
    private String to;
    private float bwUtilRate;
    private float bwThreUp;
    private float bwThreDown;
    private float delay;
    private float delayThreshold;
    private Date currentTime;
    private int alarmLevel;

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

    public float getBwUtilRate() {
        return bwUtilRate;
    }

    public void setBwUtilRate(float bwUtilRate) {
        this.bwUtilRate = bwUtilRate;
    }

    public float getBwThreUp() {
        return bwThreUp;
    }

    public void setBwThreUp(float bwThreUp) {
        this.bwThreUp = bwThreUp;
    }

    public float getBwThreDown() {
        return bwThreDown;
    }

    public void setBwThreDown(float bwThreDown) {
        this.bwThreDown = bwThreDown;
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

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public int getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(int alarmLevel) {
        this.alarmLevel = alarmLevel;
    }
}
