package zjr.assm.demo.service;

import zjr.assm.demo.po.Vnf;

import java.util.List;

public interface VnfService {
    public void deleteVnfByName(String vnfName);
    public List<Vnf> findVnfByUser(String userName);
    public void addVnf(Vnf vnf);
    public List<Vnf> getVnfList();
    public List<String> selectSfcStatus(String vnfd);
    public void deleteVnfQuery(List<String> vnfNames);
}
