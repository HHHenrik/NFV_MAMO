package zjr.assm.demo.service;

import java.util.List;

public interface ControllerSfcService {
    public int getMaxId();
    public void insertSfc(int sfcId);
    public int hasRecord();
    public List<Integer> getControllerSfcId(int sfcId);
    public void deleteControllerSfcId(int sfcId);
}
