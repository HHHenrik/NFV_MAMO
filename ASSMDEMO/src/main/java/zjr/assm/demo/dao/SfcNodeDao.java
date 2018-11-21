package zjr.assm.demo.dao;

import org.springframework.stereotype.Repository;
import zjr.assm.demo.po.SfcNode;
import zjr.assm.demo.po.SfcNodeCustom;
import zjr.assm.demo.po.Vnf;

import java.util.HashMap;
import java.util.List;

@Repository
public interface SfcNodeDao {
    List<SfcNodeCustom> finSfcNodeById(int sfcId);
    List<SfcNodeCustom> getSfcNode(int sfcId);
    int getVnfSum(int sfcId);
    void insertNodeBatch(SfcNodeCustom sfcNodeCustom);
    List<SfcNode> getSfcNodeById(int sfcId);
    Vnf selVnfByVnfName(SfcNodeCustom sfcNodeCustom);
    List<SfcNodeCustom> getVnfNodeById(HashMap map);
}
