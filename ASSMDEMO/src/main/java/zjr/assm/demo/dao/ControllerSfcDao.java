package zjr.assm.demo.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControllerSfcDao {
    int getMaxId();
    void insertSfc(int sfcId);
    int hasRecord();
    List<Integer> getControllerSfcId(int sfcId);
    void deleteControllerSfcId(int sfcId);
}
