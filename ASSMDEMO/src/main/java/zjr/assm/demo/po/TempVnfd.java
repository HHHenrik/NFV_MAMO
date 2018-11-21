package zjr.assm.demo.po;

public class TempVnfd {
    private String vnfd;
    private int numVirtualCpu;
    private int virtualMemSize;
    private int sizeOfStorage;
    private String swImageDesc;

    public String getVnfd() {
        return vnfd;
    }

    public void setVnfd(String vnfd) {
        this.vnfd = vnfd;
    }

    public int getNumVirtualCpu() {
        return numVirtualCpu;
    }

    public void setNumVirtualCpu(int numVirtualCpu) {
        this.numVirtualCpu = numVirtualCpu;
    }

    public int getVirtualMemSize() {
        return virtualMemSize;
    }

    public void setVirtualMemSize(int virtualMemSize) {
        this.virtualMemSize = virtualMemSize;
    }

    public int getSizeOfStorage() {
        return sizeOfStorage;
    }

    public void setSizeOfStorage(int sizeOfStorage) {
        this.sizeOfStorage = sizeOfStorage;
    }

    public String getSwImageDesc() {
        return swImageDesc;
    }

    public void setSwImageDesc(String swImageDesc) {
        this.swImageDesc = swImageDesc;
    }
}
