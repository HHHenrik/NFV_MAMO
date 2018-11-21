package zjr.assm.demo.dao;

import org.springframework.stereotype.Repository;
import zjr.assm.demo.po.VnfdRelevance;

@Repository
public interface VnfdRelevanceDao {
    VnfdRelevance findSpecifyVnf(String vnfd);
    void updateVnfNum(VnfdRelevance vnfdRelevance);
    void deleteVnfByVnfd(String vnfd);
    int hasMatchRecord(String vnfd);
    void insertVnfdRelevance(VnfdRelevance vnfdRelevance);
}
