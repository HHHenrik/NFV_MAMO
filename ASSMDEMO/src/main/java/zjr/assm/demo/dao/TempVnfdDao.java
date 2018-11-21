package zjr.assm.demo.dao;

import org.springframework.stereotype.Repository;
import zjr.assm.demo.po.Vnfd;

@Repository
public interface TempVnfdDao {
    void addTempVnfd(Vnfd vnfd);
    void deleteVnfByVnfd(String vnfd);
    String findSwPath(String vnfd);
}
