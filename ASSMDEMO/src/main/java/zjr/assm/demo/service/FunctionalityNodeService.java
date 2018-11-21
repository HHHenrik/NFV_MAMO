package zjr.assm.demo.service;

import zjr.assm.demo.po.FunctionalityNode;
import zjr.assm.demo.po.FunctionalityNodeCustom;

import java.util.List;

public interface FunctionalityNodeService {
    public List<FunctionalityNode> getNode();
    public FunctionalityNode getNodeById(String nodeId);
    public FunctionalityNodeCustom getNodeData(String nodeId);
}
