package zjr.assm.demo.dao;

import org.springframework.stereotype.Repository;
import zjr.assm.demo.po.Algorithm;

import java.util.List;

@Repository
public interface AlgorithmDao {
    Algorithm getDeployAlgInfo();
    List<Algorithm> getAllAlgorithm();
    int getWorkingAlgNum(String function);
    String isWorking(int id);
    void updateAlgStatus(Algorithm algorithm);
    List<Integer> getWorkingAlgId();
    void deleteAlgBatch(List<Integer> idList);
    List<String> getDeleteAlgJarPath(List<Integer> idList);
    void insertAlg(Algorithm algorithm);

    Algorithm getScaleAlgInfo();
}
