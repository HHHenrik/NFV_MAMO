package zjr.assm.demo.dao;

import org.springframework.stereotype.Repository;
import zjr.assm.demo.po.Vnf;

import java.util.List;

@Repository
public interface VnfDao {
    void deleteVnfByName(String vnfName);
    List<Vnf> findVnfByUser(String userName);
    void addVnf(Vnf vnf);
    List<Vnf> getVnfList();
    List<String> selectSfcStatus(String vnfd);
    void deleteVnfQuery(List<String> vnfNames);
}
