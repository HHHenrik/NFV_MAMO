package zjr.assm.demo.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.AlgorithmDao;
//import zjr.assm.demo.debugJar.DynamicExpansion.AlgDeploy;
import zjr.assm.demo.po.Algorithm;
import zjr.assm.demo.service.AlgorithmService;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class AlgorithmServiceImpl implements AlgorithmService {
    @Autowired
    private AlgorithmDao algorithmDao;

    public Algorithm getDeployAlgInfo() {
        return algorithmDao.getDeployAlgInfo();
    }

    public HashMap<String, Object> executeAlg(Algorithm algorithm, JSONObject phyNet, JSONObject sfcReq) throws Exception{
        HashMap<String, Object> result = new HashMap<String, Object>();
        File file=new File(algorithm.getPath());//jar����·��
        URL url=file.toURI().toURL();
        Class parentClass = Class.forName("org.json.JSONObject");
        ClassLoader classLoader = new URLClassLoader(new URL[]{url},parentClass.getClassLoader());//�����������
        Class<?> cls = classLoader.loadClass(algorithm.getClassName());//����ָ���࣬ע��һ��Ҫ������İ���
        Method method = cls.getMethod(algorithm.getFunctionName(),JSONObject.class, JSONObject.class);//�������Ͷ�Ӧ�ĸ�������������
        Object obj = cls.newInstance();
        result = (HashMap<String, Object>) method.invoke(obj, phyNet, sfcReq);
//        result = (HashMap<String, Object>) new AlgDeploy().deploy(phyNet,sfcReq);
        return  result;
    }

    public List<Algorithm> getAllAlgorithm() {
        return algorithmDao.getAllAlgorithm();
    }

    public int getWorkingAlgNum(String function) {
        return algorithmDao.getWorkingAlgNum(function);
    }

    public String isWorking(int id) {
        return algorithmDao.isWorking(id);
    }

    public void updateAlgStatus(Algorithm algorithm) {
        algorithmDao.updateAlgStatus(algorithm);
    }

    public List<Integer> getWorkingAlgId() {
        return algorithmDao.getWorkingAlgId();
    }

    public void deleteAlgBatch(List<Integer> idList) {
        algorithmDao.deleteAlgBatch(idList);
    }

    public List<String> getDeleteAlgJarPath(List<Integer> idList) {
        return algorithmDao.getDeleteAlgJarPath(idList);
    }

    public void insertAlg(Algorithm algorithm) {
        algorithmDao.insertAlg(algorithm);
    }

    @Override
    public Algorithm getScaleAlgInfo() {
        return algorithmDao.getScaleAlgInfo();
    }
}
