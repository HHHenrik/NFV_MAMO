package zjr.assm.demo.service;

import org.json.JSONObject;
import zjr.assm.demo.po.Algorithm;

import java.util.HashMap;
import java.util.List;

public interface AlgorithmService {
    public Algorithm getDeployAlgInfo();
    public HashMap<String, Object> executeAlg(Algorithm algorithm, JSONObject phyNet, JSONObject sfcReq) throws Exception;
    public List<Algorithm> getAllAlgorithm();
    public int getWorkingAlgNum(String function);
    public String isWorking(int id);
    public void updateAlgStatus(Algorithm algorithm);
    public List<Integer> getWorkingAlgId();
    public void deleteAlgBatch(List<Integer> idList);
    public List<String> getDeleteAlgJarPath(List<Integer> idList);
    public void insertAlg(Algorithm algorithm);
}
