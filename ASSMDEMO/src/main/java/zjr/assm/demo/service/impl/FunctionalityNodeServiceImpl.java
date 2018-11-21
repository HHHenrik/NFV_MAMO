package zjr.assm.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.FunctionalityNodeDao;
import zjr.assm.demo.po.FunctionalityNode;
import zjr.assm.demo.po.FunctionalityNodeCustom;
import zjr.assm.demo.service.FunctionalityNodeService;

import java.util.List;

@Transactional
@Service
public class FunctionalityNodeServiceImpl implements FunctionalityNodeService{
    @Autowired
    private FunctionalityNodeDao functionalityNodeDao;

    public List<FunctionalityNode> getNode() {
        return functionalityNodeDao.getNode();
    }

    public FunctionalityNode getNodeById(String nodeId) {
        return functionalityNodeDao.getNodeById(nodeId);
    }

    public FunctionalityNodeCustom getNodeData(String nodeId) {
        return functionalityNodeDao.getNodeData(nodeId);
    }
}
