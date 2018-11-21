package zjr.vim.staticData;

import zjr.vim.thread.*;

public class ThreadData {
    public static PhyNodeThread phyNodeThread = new PhyNodeThread();
    public static PhyLinkThread phyLinkThread = new PhyLinkThread();

    public static SfcThread sfcThread = new SfcThread();
    public static SfcLinkThread sfcLinkThread = new SfcLinkThread();
    public static SfcNodeThread sfcNodeThread = new SfcNodeThread();
}
