package zjr.assm.demo.dao;

import org.springframework.stereotype.Repository;
import zjr.assm.demo.po.Sfc;
import zjr.assm.demo.po.SfcCustom;

import java.util.List;

@Repository
public interface SfcDao {
    void addSfc(Sfc sfc);
    List<Sfc> getSfcList(String userName);
    void deleteSfc(List<Integer> sfcIdList);
    void updateSfcStatus(Sfc sfc);
    SfcCustom getSfcById(int sfcId);
    String getSfcStatus(int sfcId);
}
