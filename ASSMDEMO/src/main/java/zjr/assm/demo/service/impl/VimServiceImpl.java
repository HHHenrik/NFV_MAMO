package zjr.assm.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.VimDao;
import zjr.assm.demo.po.Vim;
import zjr.assm.demo.service.VimService;

import java.util.List;

@Transactional
@Service
public class VimServiceImpl implements VimService {
    @Autowired
    private VimDao vimDao;

    public List<String> findSpecifyVim(Vim vim) {
        return vimDao.findSpecifyVim(vim);
    }
}
