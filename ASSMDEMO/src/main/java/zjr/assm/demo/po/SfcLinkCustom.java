package zjr.assm.demo.po;

import java.util.List;

public class SfcLinkCustom extends  SfcLink{
    private int alarmLevel;
    private List<SfcLink> sfcLinkList;

    public List<SfcLink> getSfcLinkList() {
        return sfcLinkList;
    }

    public void setSfcLinkList(List<SfcLink> sfcLinkList) {
        this.sfcLinkList = sfcLinkList;
    }

    public int getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(int alarmLevel) {
        this.alarmLevel = alarmLevel;
    }
}
