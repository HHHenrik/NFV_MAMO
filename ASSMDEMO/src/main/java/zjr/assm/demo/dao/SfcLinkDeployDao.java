package zjr.assm.demo.dao;

import org.springframework.stereotype.Repository;
import zjr.assm.demo.po.SfcLinkDeploy;
import zjr.assm.demo.po.SfcLinkDeployCustom;

import java.util.List;

@Repository
public interface SfcLinkDeployDao {
    List<SfcLinkDeploy> selectLinkById(int sfcId);
    void deleteDataById(int sfcId);
    void insertLinkResBatch(SfcLinkDeployCustom sfcLinkDeployCustom);
}
