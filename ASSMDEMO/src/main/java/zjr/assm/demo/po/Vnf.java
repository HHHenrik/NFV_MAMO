package zjr.assm.demo.po;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Vnf {
    private String vnfd;
    private String vnfName;
    private String userName;
    private String vnfProductName;
    private String company;
    private String version;
    private int numVirtualCpu;
    private int sizeOfStorage;
    private int virtualMemSize;
    private Date createTime;

    public String getVnfd() {
        return vnfd;
    }

    public void setVnfd(String vnfd) {
        this.vnfd = vnfd;
    }

    public String getVnfName() {
        return vnfName;
    }

    public void setVnfName(String vnfName) {
        this.vnfName = vnfName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVnfProductName() {
        return vnfProductName;
    }

    public void setVnfProductName(String vnfProductName) {
        this.vnfProductName = vnfProductName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getNumVirtualCpu() {
        return numVirtualCpu;
    }

    public void setNumVirtualCpu(int numVirtualCpu) {
        this.numVirtualCpu = numVirtualCpu;
    }

    public int getSizeOfStorage() {
        return sizeOfStorage;
    }

    public void setSizeOfStorage(int sizeOfStorage) {
        this.sizeOfStorage = sizeOfStorage;
    }

    public int getVirtualMemSize() {
        return virtualMemSize;
    }

    public void setVirtualMemSize(int virtualMemSize) {
        this.virtualMemSize = virtualMemSize;
    }

    public String getCreateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
