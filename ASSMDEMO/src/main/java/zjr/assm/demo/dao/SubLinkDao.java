package zjr.assm.demo.dao;

import org.springframework.stereotype.Repository;
import zjr.assm.demo.po.SubLink;
import zjr.assm.demo.po.SubLinkCustom;

import java.util.HashMap;
import java.util.List;

@Repository
public interface SubLinkDao {
    List<SubLink> getTopo();
    SubLinkCustom getLink(HashMap map);
}
