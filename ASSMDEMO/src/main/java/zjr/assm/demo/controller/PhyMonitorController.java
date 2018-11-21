package zjr.assm.demo.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zjr.assm.demo.po.*;
import zjr.assm.demo.service.FunctionalityNodeService;
import zjr.assm.demo.service.PhyLinkMonitorService;
import zjr.assm.demo.service.PhyNodeMonitorService;
import zjr.assm.demo.service.SubLinkService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/html")
public class PhyMonitorController {
    @Autowired
    private PhyNodeMonitorService phyNodeMonitorService;

    @Autowired
    private PhyLinkMonitorService phyLinkMonitorService;

    @Autowired
    private FunctionalityNodeService functionalityNodeService;

    @Autowired
    private SubLinkService subLinkService;

    @RequestMapping(value = "/phyNodeData.json", method = RequestMethod.GET)
    public @ResponseBody List<FunctionalityNode> getPhyNodeData(){
        return functionalityNodeService.getNode();
    }

    @RequestMapping(value = "/phyNodeMonitorData.json", method = RequestMethod.GET)
    public @ResponseBody List<PhyNodeMonitorCustom> getPhyNodeMonitorData(){
        return phyNodeMonitorService.getNodeMonitorData();
    }

    @RequestMapping(value = "/phyLinkMonitorData.json", method = RequestMethod.GET)
    public @ResponseBody List<PhyLinkMonitorCustom> getPhyLinkMonitorData(){
        return phyLinkMonitorService.getLinkMonitorData();
    }

    @RequestMapping(value = "subNodeMonitorData.json", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getNodeMonitorData (HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<String, Object>();
        String nodeId = request.getParameter("nodeId");
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        if (beginTime.equals("-1")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = df.format(new Date());
            beginTime = currentDate + " 00:00:00";
            endTime = currentDate + " 23:59:59";
//            beginTime = "2018-04-30 00:00:00";
//            endTime = "2018-04-30 23:59:59";
        }
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("nodeId", nodeId); hm.put("beginTime", beginTime); hm.put("endTime", endTime);
        List<PhyNodeMonitorCustom> phyNodeMonitorCustoms = phyNodeMonitorService.getPhyNodeMonitorData(hm);
        FunctionalityNodeCustom functionalityNodeCustom = functionalityNodeService.getNodeData(nodeId);
        functionalityNodeCustom.setAlarmLevel(phyNodeMonitorCustoms.get(phyNodeMonitorCustoms.size() - 1).getAlarmLevel());

        JSONArray nodeArray = new JSONArray();
        JSONObject nodeObject = new JSONObject();
        JSONArray nodeChildren = new JSONArray();
        JSONObject nodeData = new JSONObject();
        nodeData.put("name", "制造商:" + functionalityNodeCustom.getManufacturer()); nodeChildren.put(nodeData);
        nodeData = new JSONObject();
        nodeData.put("name", "可用CPU:" + functionalityNodeCustom.getAvaCpu()); nodeChildren.put(nodeData);
        nodeData = new JSONObject();
        nodeData.put("name", "全部CPU:" + functionalityNodeCustom.getTotalCpu()); nodeChildren.put(nodeData);
        nodeData = new JSONObject();
        nodeData.put("name", "可用内存:" + functionalityNodeCustom.getAvaMemory()); nodeChildren.put(nodeData);
        nodeData = new JSONObject();
        nodeData.put("name", "全部内存:" +functionalityNodeCustom.getTotalMemory()); nodeChildren.put(nodeData);
        nodeData = new JSONObject();
        nodeData.put("name", "可用存储:" + functionalityNodeCustom.getAvaStorage()); nodeChildren.put(nodeData);
        nodeData = new JSONObject();
        nodeData.put("name", "全部存储:" + functionalityNodeCustom.getTotalStorage()); nodeChildren.put(nodeData);
        nodeData = new JSONObject();
        nodeData.put("name", "告警级别:" + functionalityNodeCustom.getAlarmLevel()); nodeChildren.put(nodeData);
        nodeObject.put("name",functionalityNodeCustom.getNodeId()); nodeObject.put("children", nodeChildren); nodeArray.put(nodeObject);
        nodeObject = new JSONObject(); nodeObject.put("name","Cpu_Util_Rate"); nodeArray.put(nodeObject);
        nodeObject = new JSONObject(); nodeObject.put("name","Memory_Util_Rate"); nodeArray.put(nodeObject);
        nodeObject = new JSONObject(); nodeObject.put("name","Storage_Util_Rate"); nodeArray.put(nodeObject);
        map.put("nodeData", nodeArray.toString());

        JSONArray cpuUtilArray = new JSONArray();
        JSONArray cpuThreUp = new JSONArray();
        JSONArray cpuThreDown = new JSONArray();
        JSONArray memUtilArray = new JSONArray();
        JSONArray memThreUp = new JSONArray();
        JSONArray memThreDown = new JSONArray();
        JSONArray storageUtilArray = new JSONArray();
        JSONArray storageThreUp = new JSONArray();
        JSONArray storageThreDown = new JSONArray();
        JSONArray dateArray = new JSONArray();
        for (int i = 0; i < phyNodeMonitorCustoms.size(); i++){
            cpuUtilArray.put(phyNodeMonitorCustoms.get(i).getCpuUtilRate());
            cpuThreUp.put(phyNodeMonitorCustoms.get(i).getCpuThreUp());
            cpuThreDown.put(phyNodeMonitorCustoms.get(i).getCpuThreDown());
            memUtilArray.put(phyNodeMonitorCustoms.get(i).getMemoryUtilRate());
            memThreUp.put(phyNodeMonitorCustoms.get(i).getMemoryThreUp());
            memThreDown.put(phyNodeMonitorCustoms.get(i).getMemoryThreDown());
            storageUtilArray.put(phyNodeMonitorCustoms.get(i).getStorageUtilRate());
            storageThreUp.put(phyNodeMonitorCustoms.get(i).getStorageThreUp());
            storageThreDown.put(phyNodeMonitorCustoms.get(i).getStorageThreDown());
            dateArray.put(phyNodeMonitorCustoms.get(i).getCurrentTime());
        }
        map.put("cpuUtilArray", cpuUtilArray.toString()); map.put("cpuThreUp", cpuThreUp.toString());
        map.put("cpuThreDown", cpuThreDown.toString()); map.put("memUtilArray", memUtilArray.toString());
        map.put("memThreUp", memThreUp.toString()); map.put("memThreDown", memThreDown.toString());
        map.put("storageUtilArray", storageUtilArray.toString()); map.put("storageThreUp", storageThreUp.toString());
        map.put("storageThreDown", storageThreDown.toString()); map.put("dateArray", dateArray.toString());
        return map;
    }

    @RequestMapping(value = "subLinkMonitorData.json", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getLinkMonitorData (HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<String, Object>();
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        if (beginTime.equals("-1")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = df.format(new Date());
            beginTime = currentDate + " 00:00:00";
            endTime = currentDate + " 23:59:59";
//            beginTime = "2018-04-30 00:00:00";
//            endTime = "2018-04-30 23:59:59";
        }
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("from", from); hm.put("to", to); hm.put("beginTime", beginTime); hm.put("endTime", endTime);
        List<PhyLinkMonitorCustom> phyLinkMonitorCustoms = phyLinkMonitorService.getPhyLinkMonitorData(hm);

        SubLinkCustom subLinkCustom = subLinkService.getLink(hm);
        subLinkCustom.setAlarmLevel(phyLinkMonitorCustoms.get(phyLinkMonitorCustoms.size() - 1).getAlarmLevel());

        JSONArray linkArray = new JSONArray();
        JSONObject linkObject = new JSONObject();
        JSONArray linkChildren = new JSONArray();
        JSONObject linkData = new JSONObject();
        linkData.put("name", "起始节点:" + subLinkCustom.getFrom()); linkChildren.put(linkData);
        linkData = new JSONObject();
        linkData.put("name", "终止节点:" + subLinkCustom.getTo()); linkChildren.put(linkData);
        linkData = new JSONObject();
        linkData.put("name", "起始端口:" + subLinkCustom.getFromDpId()); linkChildren.put(linkData);
        linkData = new JSONObject();
        linkData.put("name", "终止端口:" + subLinkCustom.getToDpId()); linkChildren.put(linkData);
        linkData = new JSONObject();
        linkData.put("name", "带宽:" +subLinkCustom.getBandwidth()); linkChildren.put(linkData);
        linkData = new JSONObject();
        linkData.put("name", "延迟:" + subLinkCustom.getDelay()); linkChildren.put(linkData);
        linkData = new JSONObject();
        linkData.put("name", "告警级别:" + subLinkCustom.getAlarmLevel()); linkChildren.put(linkData);
        linkObject.put("name", "链路信息"); linkObject.put("children", linkChildren); linkArray.put(linkObject);
        linkObject = new JSONObject(); linkObject.put("name","Bw_Util_Rate"); linkArray.put(linkObject);
        linkObject = new JSONObject(); linkObject.put("name","Delay"); linkArray.put(linkObject);
        map.put("linkData", linkArray.toString());

        JSONArray bwUtilArray = new JSONArray();
        JSONArray bwThreUp = new JSONArray();
        JSONArray bwThreDown = new JSONArray();
        JSONArray delay = new JSONArray();
        JSONArray delayThreshold = new JSONArray();
        JSONArray dateArray = new JSONArray();
        for (int i = 0; i < phyLinkMonitorCustoms.size(); i++){
            bwUtilArray.put(phyLinkMonitorCustoms.get(i).getBwUtilRate());
            bwThreUp.put(phyLinkMonitorCustoms.get(i).getBwThreUp());
            bwThreDown.put(phyLinkMonitorCustoms.get(i).getBwThreDown());
            delay.put(phyLinkMonitorCustoms.get(i).getDelay());
            delayThreshold.put(phyLinkMonitorCustoms.get(i).getDelayThreshold());
            dateArray.put(phyLinkMonitorCustoms.get(i).getCurrentTime());
        }
        map.put("bwUtilArray", bwUtilArray.toString()); map.put("bwThreUp", bwThreUp.toString());
        map.put("bwThreDown", bwThreDown.toString()); map.put("delay", delay.toString());
        map.put("delayThreshold", delayThreshold.toString()); map.put("dateArray", dateArray.toString());
        return map;
    }
}
