package zjr.assm.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.ForwardingNodeDao;
import zjr.assm.demo.po.ForwardingNode;
import zjr.assm.demo.service.ForwardingNodeService;

@Transactional
@Service
public class ForwardingNodeServiceImpl implements ForwardingNodeService{
    @Autowired
    private ForwardingNodeDao forwardingNodeDao;

    public ForwardingNode getNodeById(String nodeId) {
        return forwardingNodeDao.getNodeById(nodeId);
    }
}
