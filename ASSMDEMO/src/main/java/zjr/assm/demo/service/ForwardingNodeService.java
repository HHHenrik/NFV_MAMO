package zjr.assm.demo.service;

import zjr.assm.demo.po.ForwardingNode;

public interface ForwardingNodeService {
    public ForwardingNode getNodeById(String nodeId);

    public int getForwardingNodeNum();
}
