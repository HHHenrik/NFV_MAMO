package zjr.assm.demo.po;

import java.util.Date;

public class PhyNodeMonitor {
    private String nodeId;
    private Date currentTime;
    private int alarmLevel;
    private float cpuUtilRate;
    private float memoryUtilRate;
    private float storageUtilRate;
    private float cpuThreUp;
    private float cpuThreDown;
    private float memoryThreUp;
    private float memoryThreDown;
    private float storageThreUp;
    private float storageThreDown;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
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

    public float getCpuThreUp() {
        return cpuThreUp;
    }

    public void setCpuThreUp(float cpuThreUp) {
        this.cpuThreUp = cpuThreUp;
    }

    public float getCpuThreDown() {
        return cpuThreDown;
    }

    public void setCpuThreDown(float cpuThreDown) {
        this.cpuThreDown = cpuThreDown;
    }

    public float getMemoryThreUp() {
        return memoryThreUp;
    }

    public void setMemoryThreUp(float memoryThreUp) {
        this.memoryThreUp = memoryThreUp;
    }

    public float getMemoryThreDown() {
        return memoryThreDown;
    }

    public void setMemoryThreDown(float memoryThreDown) {
        this.memoryThreDown = memoryThreDown;
    }

    public float getStorageThreUp() {
        return storageThreUp;
    }

    public void setStorageThreUp(float storageThreUp) {
        this.storageThreUp = storageThreUp;
    }

    public float getStorageThreDown() {
        return storageThreDown;
    }

    public void setStorageThreDown(float storageThreDown) {
        this.storageThreDown = storageThreDown;
    }
}
