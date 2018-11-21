package zjr.assm.demo.po;

import java.util.List;

public class SfcNodeCustom extends SfcNode{
//    private String vnfd;
//    private int vnfdId;
//    private int sfcId;
    private int count;
    private int cpu;
    private int memory;
    private int storage;
    private String vnfProductName;
    private String userName;
    private String company;
    private int alarmLevel;

    private List<SfcNode> sfcNodeList;

//    @Override
//    public String getVnfd() {
//        return vnfd;
//    }

//    @Override
//    public void setVnfd(String vnfd) {
//        this.vnfd = vnfd;
//    }

//    @Override
//    public int getSfcId() {
//        return sfcId;
//    }
//
//    @Override
//    public void setSfcId(int sfcId) {
//        this.sfcId = sfcId;
//    }

//    @Override
//    public int getVnfdId() {
//        return vnfdId;
//    }
//
//    @Override
//    public void setVnfdId(int vnfdId) {
//        this.vnfdId = vnfdId;
//    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCpu() {
        return cpu;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public List<SfcNode> getSfcNodeList() {
        return sfcNodeList;
    }

    public void setSfcNodeList(List<SfcNode> sfcNodeList) {
        this.sfcNodeList = sfcNodeList;
    }

    public String getVnfProductName() {
        return vnfProductName;
    }

    public void setVnfProductName(String vnfProductName) {
        this.vnfProductName = vnfProductName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(int alarmLevel) {
        this.alarmLevel = alarmLevel;
    }
}
