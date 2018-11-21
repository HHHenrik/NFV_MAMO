package zjr.assm.demo.po;

import java.util.Date;

public class Vnfd {
    private String vnfd;
    private String version;
    private String company;
    private String vnfProductName;
    private int virtualMemSize;
    private String cpuArchiecture;
    private int numVirtualCpu;
    private float virtualCpuClock;
    private String typeOfStorage;
    private int sizeOfStorage;
    private String vnfSoftwareVersion;
    private String swImageDesc;
    private String cpd;
    private String virtualEnviroment;
    private Date createTime;
    private Date updateTime;

    public String getVnfd() {
        return vnfd;
    }

    public void setVnfd(String vnfd) {
        this.vnfd = vnfd;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getVnfProductName() {
        return vnfProductName;
    }

    public void setVnfProductName(String vnfProductName) {
        this.vnfProductName = vnfProductName;
    }

    public int getVirtualMemSize() {
        return virtualMemSize;
    }

    public void setVirtualMemSize(int virtualMemSize) {
        this.virtualMemSize = virtualMemSize;
    }

    public String getCpuArchiecture() {
        return cpuArchiecture;
    }

    public void setCpuArchiecture(String cpuArchiecture) {
        this.cpuArchiecture = cpuArchiecture;
    }

    public int getNumVirtualCpu() {
        return numVirtualCpu;
    }

    public void setNumVirtualCpu(int numVirtualCpu) {
        this.numVirtualCpu = numVirtualCpu;
    }

    public float getVirtualCpuClock() {
        return virtualCpuClock;
    }

    public void setVirtualCpuClock(float virtualCpuClock) {
        this.virtualCpuClock = virtualCpuClock;
    }

    public String getTypeOfStorage() {
        return typeOfStorage;
    }

    public void setTypeOfStorage(String typeOfStorage) {
        this.typeOfStorage = typeOfStorage;
    }

    public int getSizeOfStorage() {
        return sizeOfStorage;
    }

    public void setSizeOfStorage(int sizeOfStorage) {
        this.sizeOfStorage = sizeOfStorage;
    }

    public String getVnfSoftwareVersion() {
        return vnfSoftwareVersion;
    }

    public void setVnfSoftwareVersion(String vnfSoftwareVersion) {
        this.vnfSoftwareVersion = vnfSoftwareVersion;
    }

    public String getSwImageDesc() {
        return swImageDesc;
    }

    public void setSwImageDesc(String swImageDesc) {
        this.swImageDesc = swImageDesc;
    }

    public String getCpd() {
        return cpd;
    }

    public void setCpd(String cpd) {
        this.cpd = cpd;
    }

    public String getVirtualEnviroment() {
        return virtualEnviroment;
    }

    public void setVirtualEnviroment(String virtualEnviroment) {
        this.virtualEnviroment = virtualEnviroment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
