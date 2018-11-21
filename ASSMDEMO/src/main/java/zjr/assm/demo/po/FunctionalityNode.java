package zjr.assm.demo.po;

public class FunctionalityNode {
    private String nodeId;
    private int totalCpu;
    private int totalMemory;
    private int totalStorage;
    private String manufacturer;
    private int avaCpu;
    private int avaMemory;
    private int avaStorage;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public int getTotalCpu() {
        return totalCpu;
    }

    public void setTotalCpu(int totalCpu) {
        this.totalCpu = totalCpu;
    }

    public int getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(int totalMemory) {
        this.totalMemory = totalMemory;
    }

    public int getTotalStorage() {
        return totalStorage;
    }

    public void setTotalStorage(int totalStorage) {
        this.totalStorage = totalStorage;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getAvaCpu() {
        return avaCpu;
    }

    public void setAvaCpu(int avaCpu) {
        this.avaCpu = avaCpu;
    }

    public int getAvaMemory() {
        return avaMemory;
    }

    public void setAvaMemory(int avaMemory) {
        this.avaMemory = avaMemory;
    }

    public int getAvaStorage() {
        return avaStorage;
    }

    public void setAvaStorage(int avaStorage) {
        this.avaStorage = avaStorage;
    }
}
