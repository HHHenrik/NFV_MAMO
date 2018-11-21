package zjr.assm.demo.service;

import zjr.assm.demo.po.Vnfd;

public interface TempVnfdService {
    public void addTempVnfd(Vnfd vnfd);
    public void deleteVnfByVnfd(String vnfd);
    public String findSwPath(String vnfd);
}
