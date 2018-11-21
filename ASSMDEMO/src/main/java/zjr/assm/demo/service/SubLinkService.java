package zjr.assm.demo.service;

import zjr.assm.demo.po.SubLink;
import zjr.assm.demo.po.SubLinkCustom;

import java.util.HashMap;
import java.util.List;

public interface SubLinkService {
    public List<SubLink> getTopo();
    public SubLinkCustom getLink(HashMap map);
}
