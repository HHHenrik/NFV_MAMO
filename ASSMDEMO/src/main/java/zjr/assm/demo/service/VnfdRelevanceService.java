package zjr.assm.demo.service;

import zjr.assm.demo.po.VnfdRelevance;

public interface VnfdRelevanceService {
    VnfdRelevance findSpecifyVnf(String vnfd);
    void updateVnfNum(VnfdRelevance vnfdRelevance);
    void deleteVnfByVnfd(String vnfd);
    int hasMatchRecord(String vnfd);
    void insertVnfdRelevance(VnfdRelevance vnfdRelevance);
}
