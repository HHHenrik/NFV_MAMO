import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ControllerHelper
{
    private static StaticFlowPusher sfp = new StaticFlowPusher("10.0.0.2",8080);

    private static HashMap<Integer,List> all_entries = new HashMap<Integer, List>();

    public static void SetControllerParam(String ip,int port)
    {
        sfp = new StaticFlowPusher(ip,port);
    }

    public static int ClearEntries()
    {
        return sfp.clear_entry();
    }

    public static int DisableEntry(int sfcid)
    {
        List<HashMap> entries = (List<HashMap>) all_entries.get(sfcid);
        return sfp.delete_entry(entries.get(0));
    }

    public static int EnableEntry(int sfcid)
    {
        List<HashMap> entries = (List<HashMap>) all_entries.get(sfcid);
        return sfp.set_entry(entries.get(0));
    }

    public static int DeleteEntries(int sfcid)
    {
        int ret = 200;
        List<HashMap> entries = (List<HashMap>) all_entries.get(sfcid);
        for (HashMap e : entries)
        {
            ret = sfp.delete_entry(e);
            if(ret != 200)
                return ret;
        }
        all_entries.remove(sfcid);
        return ret;
    }

    public static int CreateEntries(List<Integer> nodes, int sfcid)
    {
        List<HashMap> entries = new ArrayList<HashMap>();
        HashMap entry = new HashMap();
        int len = nodes.size();
        int entry_count = 3;
        int ret;

        String eth_src = Integer.toHexString(nodes.get(0));
        if(eth_src.length() == 1)
            eth_src = "0" + eth_src;
        eth_src = "00:00:00:00:00:" + eth_src;
        String eth_dst = Integer.toHexString(nodes.get(len-1));
        if(eth_dst.length()==1)
            eth_dst = "0" + eth_dst;
        eth_dst = "00:00:00:00:00:" + eth_dst;

        entry.put("switch","00:00:00:00:00:" +  Integer.toHexString(nodes.get(0)));
        entry.put("name","flow_"+Integer.toString(entry_count) + "_" + Integer.toString(sfcid));
        entry.put("cookie","0");
        entry.put("priority","32768");
        entry.put("in_port","1");
        entry.put("eth_src",eth_src);
        entry.put("eth_dst",eth_dst);
        entry.put("active","true");
        entry.put("actions","push_vlan=0x8100,set_field=eth_vlan_vid->" + Integer.toString(entry_count) + ",output=" + Integer.toString(nodes.get(1)));
        entry_count++;
        entries.add(entry);
        ret = sfp.set_entry(entry);
        if(ret != 200)
            return ret;

        for(int i = 1;i<len - 1;i++)
        {
            entry = new HashMap();
            entry.put("switch","00:00:00:00:00:" +  Integer.toHexString(nodes.get(i)));
            entry.put("name","flow_"+Integer.toString(entry_count) + "_" + Integer.toString(sfcid));
            entry.put("cookie","0");
            entry.put("priority","32768");
            entry.put("eth_vlan_vid",Integer.toString(entry_count - 1 + 0x1000));
            entry.put("in_port",Integer.toString(nodes.get(i-1)));
            entry.put("eth_src",eth_src);
            entry.put("eth_dst",eth_dst);
            entry.put("active","true");
            String actions = "set_field=eth_vlan_vid->" + Integer.toString(entry_count) + ",output=";
            if(nodes.get(i-1) == nodes.get(i+1))
                actions = actions + "in_port";
            else
                actions = actions + Integer.toString(nodes.get(i+1));
            entry.put("actions",actions);
            entry_count++;
            entries.add(entry);
            ret = sfp.set_entry(entry);

            if(ret != 200)
                return ret;
        }
        entry = new HashMap();
        entry.put("switch","00:00:00:00:00:" +  Integer.toHexString(nodes.get(len-1)));
        entry.put("name","flow_"+Integer.toString(entry_count) + "_" + Integer.toString(sfcid));
        entry.put("cookie","0");
        entry.put("priority","32768");
        entry.put("eth_vlan_vid",Integer.toString(entry_count - 1 + 0x1000));
        entry.put("in_port",Integer.toString(nodes.get(len-2)));
        entry.put("eth_src",eth_src);
        entry.put("eth_dst",eth_dst);
        entry.put("active","true");
        entry.put("actions","pop_vlan,output=1");
        entry_count++;
        entries.add(entry);
        ret = sfp.set_entry(entry);

        all_entries.put(sfcid,entries);

        return ret;
    }
}
