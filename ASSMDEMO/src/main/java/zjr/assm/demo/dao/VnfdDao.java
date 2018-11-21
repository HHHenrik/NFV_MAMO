package zjr.assm.demo.dao;

import org.springframework.stereotype.Repository;
import zjr.assm.demo.po.Vnfd;
import zjr.assm.demo.po.VnfdCustom;

import java.util.List;

@Repository
public interface VnfdDao {
    List<Vnfd> getVnfdList();
    void deleteVnfd(Vnfd vnfd);
    void deleteVnfdBatch(VnfdCustom vnfdCustom);
    void addVnfd(Vnfd vnfd);
    int hasInvalidVnf(VnfdCustom vnfdCustom);
    Vnfd findVnfByVnfd(Vnfd vnfd);
}
