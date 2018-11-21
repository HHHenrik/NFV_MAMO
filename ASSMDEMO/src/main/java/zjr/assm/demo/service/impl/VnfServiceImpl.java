package zjr.assm.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.VnfDao;
import zjr.assm.demo.po.Vnf;
import zjr.assm.demo.service.VnfService;

import java.util.List;

@Transactional
@Service
public class VnfServiceImpl implements VnfService{
    @Autowired
    private VnfDao vnfDao;
    public List<Vnf> getVnfList(){
        return vnfDao.getVnfList();
    }

    public void deleteVnfByName(String vnfName) {
        vnfDao.deleteVnfByName(vnfName);
    }

    public void addVnf(Vnf vnf){
        vnfDao.addVnf(vnf);
    }

    public List<Vnf> findVnfByUser(String userName) {
        return vnfDao.findVnfByUser(userName);
    }
    public List<String> selectSfcStatus(String vnfd){
        return vnfDao.selectSfcStatus(vnfd);
    }
    public void deleteVnfQuery(List<String> vnfNames){
//        for(int i = 0;i<vnfNames.size();i++){
//            vnfDao.deleteVnfByName(vnfNames.get(i));
//        }
        vnfDao.deleteVnfQuery(vnfNames);
    }
}
