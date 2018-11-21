package zjr.assm.demo.service;

import zjr.assm.demo.po.SfcNode;
import zjr.assm.demo.po.SfcNodeCustom;
import zjr.assm.demo.po.Vnf;

import java.util.HashMap;
import java.util.List;

public interface SfcNodeService {
    public List<SfcNodeCustom> finSfcNodeById(int sfcId);
    public List<SfcNodeCustom> getSfcNode(int sfcId);
    public int getVnfSum(int sfcId);
    public void insertNodeBatch(SfcNodeCustom sfcNodeCustom);
    public List<SfcNode> getSfcNodeById(int sfcId);
    public Vnf selVnfByVnfName(SfcNodeCustom sfcNodeCustom);
    public List<SfcNodeCustom> getVnfNodeById(HashMap map);
}
