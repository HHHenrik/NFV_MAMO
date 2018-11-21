package zjr.assm.demo.dao;

import org.springframework.stereotype.Repository;
import zjr.assm.demo.po.VnfDeploy;
import zjr.assm.demo.po.VnfDeployCustom;

import java.util.List;

@Repository
public interface VnfDeployDao {
    void insertBactch(VnfDeployCustom vnfDeployCustom);
    List<VnfDeploy> selectResBySfcId(int sfcId);
    void deleteDataById(int sfcId);
}
