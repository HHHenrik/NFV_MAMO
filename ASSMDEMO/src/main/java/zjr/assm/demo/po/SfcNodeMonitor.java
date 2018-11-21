package zjr.assm.demo.po;

import java.util.Date;

public class SfcNodeMonitor {
    private int sfcId;
    private int vnfId;
    private Date currentTime;
    private String vnfd;
    private float cpuUtilRate;
    private float memoryUtilRate;
    private float storageUtilRate;
    private float packageReceive;
    private float packageDeal;
    private float cpuThresholdUp;
    private float memoryThresholdUp;
    private float storageThresholdUp;
    private float cpuThresholdDown;
    private float memoryThresholdDown;
    private float storageThresholdDown;
    private int alarmLevel;

    public int getSfcId() {
        return sfcId;
    }

    public void setSfcId(int sfcId) {
        this.sfcId = sfcId;
    }

    public int getVnfId() {
        return vnfId;
    }

    public void setVnfId(int vnfId) {
        this.vnfId = vnfId;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public String getVnfd() {
        return vnfd;
    }

    public void setVnfd(String vnfd) {
        this.vnfd = vnfd;
    }

    public float getCpuUtilRate() {
        return cpuUtilRate;
    }

    public void setCpuUtilRate(float cpuUtilRate) {
        this.cpuUtilRate = cpuUtilRate;
    }

    public float getMemoryUtilRate() {
        return memoryUtilRate;
    }

    public void setMemoryUtilRate(float memoryUtilRate) {
        this.memoryUtilRate = memoryUtilRate;
    }

    public float getStorageUtilRate() {
        return storageUtilRate;
    }

    public void setStorageUtilRate(float storageUtilRate) {
        this.storageUtilRate = storageUtilRate;
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

    public int getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(int alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public float getCpuThresholdUp() {
        return cpuThresholdUp;
    }

    public void setCpuThresholdUp(float cpuThresholdUp) {
        this.cpuThresholdUp = cpuThresholdUp;
    }

    public float getMemoryThresholdUp() {
        return memoryThresholdUp;
    }

    public void setMemoryThresholdUp(float memoryThresholdUp) {
        this.memoryThresholdUp = memoryThresholdUp;
    }

    public float getStorageThresholdUp() {
        return storageThresholdUp;
    }

    public void setStorageThresholdUp(float storageThresholdUp) {
        this.storageThresholdUp = storageThresholdUp;
    }

    public float getCpuThresholdDown() {
        return cpuThresholdDown;
    }

    public void setCpuThresholdDown(float cpuThresholdDown) {
        this.cpuThresholdDown = cpuThresholdDown;
    }

    public float getMemoryThresholdDown() {
        return memoryThresholdDown;
    }

    public void setMemoryThresholdDown(float memoryThresholdDown) {
        this.memoryThresholdDown = memoryThresholdDown;
    }

    public float getStorageThresholdDown() {
        return storageThresholdDown;
    }

    public void setStorageThresholdDown(float storageThresholdDown) {
        this.storageThresholdDown = storageThresholdDown;
    }
}
