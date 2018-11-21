package zjr.assm.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.ControllerSfcDao;
import zjr.assm.demo.service.ControllerSfcService;

import java.util.List;

@Transactional
@Service
public class ControllerSfcServiceImpl implements ControllerSfcService{
    @Autowired
    private ControllerSfcDao controllerSfcDao;

    public int getMaxId() {
        return controllerSfcDao.getMaxId();
    }

    public void insertSfc(int sfcId) {
        controllerSfcDao.insertSfc(sfcId);
    }

    public int hasRecord() {
        return controllerSfcDao.hasRecord();
    }

    public List<Integer> getControllerSfcId(int sfcId) {
        return controllerSfcDao.getControllerSfcId(sfcId);
    }

    public void deleteControllerSfcId(int sfcId) {
        controllerSfcDao.deleteControllerSfcId(sfcId);
    }
}
