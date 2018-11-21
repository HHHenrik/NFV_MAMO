package zjr.assm.demo.service;

import zjr.assm.demo.po.Vnfd;
import zjr.assm.demo.po.VnfdCustom;

import java.util.List;

public interface VnfdService {
    public List<Vnfd> getVnfdList();
    public void deleteVnfd(Vnfd vnfd);
    public void deleteVnfdBatch(VnfdCustom vnfdCustom);
    public void addVnfd(Vnfd vnfd);
    public Vnfd findVnfByVnfd(Vnfd vnfd);
    public int hasInvalidVnf(VnfdCustom vnfdCustom);
}
