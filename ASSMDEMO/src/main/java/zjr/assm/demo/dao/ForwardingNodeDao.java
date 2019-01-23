package zjr.assm.demo.dao;

import org.springframework.stereotype.Repository;
import zjr.assm.demo.po.ForwardingNode;

@Repository
public interface ForwardingNodeDao {
    ForwardingNode getNodeById(String nodeId);

    int getForwardingNodeNum();
}
