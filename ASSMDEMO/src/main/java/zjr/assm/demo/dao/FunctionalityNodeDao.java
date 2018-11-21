package zjr.assm.demo.dao;

import org.springframework.stereotype.Repository;
import zjr.assm.demo.po.FunctionalityNode;
import zjr.assm.demo.po.FunctionalityNodeCustom;

import java.util.List;

@Repository
public interface FunctionalityNodeDao {
    List<FunctionalityNode> getNode();
    FunctionalityNode getNodeById(String nodeId);
    FunctionalityNodeCustom getNodeData(String nodeId);
}
