package zjr.assm.demo.service;

import zjr.assm.demo.po.Sfc;
import zjr.assm.demo.po.SfcCustom;

import java.util.List;

public interface SfcService {
    public void addSfc(Sfc sfc);
    public List<Sfc> getSfcList(String userName);
    public void deleteSfc(List<Integer> sfcIdList);
    public void updateSfcStatus(Sfc sfc);
    public SfcCustom getSfcById(int sfcId);
    public String getSfcStatus(int sfcId);

}
