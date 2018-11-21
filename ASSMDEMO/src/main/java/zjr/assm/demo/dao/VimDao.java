package zjr.assm.demo.dao;

import org.springframework.stereotype.Repository;
import zjr.assm.demo.po.Vim;

import java.util.List;

@Repository
public interface VimDao {
    List<String> findSpecifyVim(Vim vim);
}
