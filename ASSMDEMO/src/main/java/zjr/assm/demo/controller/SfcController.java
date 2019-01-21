package zjr.assm.demo.controller;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zjr.assm.demo.po.*;
import zjr.assm.demo.service.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/html")
public class SfcController {
    @Autowired
    private SfcService sfcService;

    @Autowired
    private SfcNodeService sfcNodeService;

    @Autowired
    private SfcLinkService sfcLinkService;

    @Autowired
    private SubLinkService subLinkService;

    @Autowired
    private FunctionalityNodeService functionalityNodeService;

    @Autowired
    private AlgorithmService algorithmService;

    @Autowired
    private VnfDeployService vnfDeployService;

    @Autowired
    private VnfdRelevanceService vnfdRelevanceService;

    @Autowired
    private TempVnfdService tempVnfdService;

    @Autowired
    private SfcLinkDeployService sfcLinkDeployService;

    @Autowired
    private ForwardingNodeService forwardingNodeService;

    @Autowired
    private SfcMonitorService sfcMonitorService;

    @Autowired
    private SfcNodeMonitorService sfcNodeMonitorService;

    @Autowired
    private SfcLinkMonitorService sfcLinkMonitorService;

    @Autowired
    private ControllerSfcService controllerSfcService;

    @RequestMapping(value = "/sfcList.json", method = RequestMethod.GET)
    public @ResponseBody List<Sfc> getsfcList(HttpServletRequest request){
        String userName = request.getParameter("userName");
        return sfcService.getSfcList(userName);
    }

    @RequestMapping(value = "/deploySfc", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> deploySfc(HttpServletRequest request){
        Map<String, Object> code = new HashMap<String, Object>();
        int count = algorithmService.getWorkingAlgNum("deploy");
        if(count == 0){ // �������㷨
            code.put("status", 2);
            return code;
        }
        HashMap<Integer, String> idAndVnfd = new HashMap<Integer, String>();
        int flag;
        int sfcId = Integer.valueOf(request.getParameter("sfcId"));
        JSONObject sfcReq = new JSONObject();
        JSONArray vnfArray = new JSONArray();
        JSONArray routesArray = new JSONArray();
        JSONArray route = new JSONArray();
        try{
            List<SfcNodeCustom> sfcNodeCustoms = sfcNodeService.getSfcNode(sfcId); //��ȡSFC�е�VNF
            int sfcVnfNum = sfcNodeService.getVnfSum(sfcId);
            if(sfcVnfNum != sfcNodeCustoms.size()){
                code.put("status", -1); //SFC�д��ڲ��Ϸ�VNF,��ɾ��!
                return code;
            }
            //��� vnf �� json
            for (int i = 0; i < sfcNodeCustoms.size(); i++){
                SfcNodeCustom sfcNodeCustom = sfcNodeCustoms.get(i);
                JSONObject vnf = new JSONObject();
                vnf.put("vnfId", sfcNodeCustom.getVnfdId());
                vnf.put("virtualMemSize", sfcNodeCustom.getMemory());
                vnf.put("numVirtualCpu", sfcNodeCustom.getCpu());
                vnf.put("sizeOfStorage", sfcNodeCustom.getStorage());
                vnfArray.put(vnf);
                idAndVnfd.put(sfcNodeCustom.getVnfdId(), sfcNodeCustom.getVnfd());
            }
            List<SfcLink> sfcLinks = sfcLinkService.selectLinkById(sfcId);
            flag = sfcLinks.get(0).getFlag(); //SFC�еķ�֧
            //��sfcLink
            for(int i = 0; i < sfcLinks.size(); i++){
                SfcLink sfcLink = sfcLinks.get(i); JSONObject link = new JSONObject();
                link.put("from",sfcLink.getFrom()); link.put("to",sfcLink.getTo());
                link.put("bandwidth", sfcLink.getBandwidth()); link.put("delay", sfcLink.getDelay());
                if (sfcLink.getFlag() != flag){ //������ַ�֧
                    routesArray.put(route);
                    flag = sfcLink.getFlag();
                    route = new JSONArray(); //�����µ� root��֧ Json
                    route.put(link);
                }else{
                    route.put(link);  //����ԭ���ķ�֧����ֱ�Ӽ���
                }
            }
            if(route.length() != 0) routesArray.put(route);
            sfcReq.put("routes",routesArray);
            sfcReq.put("VNFs", vnfArray);


            List<SubLink> subLinks = subLinkService.getTopo();
            //���������·�� json
            JSONObject phyNet = new JSONObject();
            JSONArray topo = new JSONArray();
            for (int i = 0; i < subLinks.size(); i++){
                SubLink subLink = subLinks.get(i);
                JSONObject link = new JSONObject();
                link.put("from",subLink.getFrom()); link.put("to",subLink.getTo());
                link.put("fromDpId",subLink.getFromDpId()); link.put("toDpId",subLink.getToDpId());
                link.put("bandwidth",subLink.getBandwidth()); link.put("delay",subLink.getDelay());
                topo.put(link);
            }
            phyNet.put("topo",topo);
            //��� ����ڵ㵽 josn
            List<FunctionalityNode> functionalityNodes = functionalityNodeService.getNode();
            JSONArray nodes = new JSONArray();
            for (int i = 0; i < functionalityNodes.size(); i++){
                FunctionalityNode functionalityNode = functionalityNodes.get(i);
                JSONObject node = new JSONObject();
                node.put("nodeId", functionalityNode.getNodeId()); node.put("cpu", functionalityNode.getAvaCpu());
                node.put("memory",functionalityNode.getAvaMemory()); node.put("storage",functionalityNode.getAvaStorage());
                nodes.put(node);
            }
            phyNet.put("functionalityNode",nodes);

            //��ȡ��ǰ�Ĳ����㷨 ͬ�����͵��㷨ֻ����һ������ working ״̬
            Algorithm algorithm = algorithmService.getDeployAlgInfo();
                                /*ִ�и��㷨*/
            HashMap<String, Object> res = algorithmService.executeAlg(algorithm, phyNet, sfcReq);
            JSONObject deployRes = (JSONObject) res.get("result"); //�㷨������
            List<List<String>> linkRes = (List<List<String>>)res.get("linkResult");

            Sfc sfc = new Sfc(); sfc.setSfcId(sfcId); sfc.setStatus("working");
            sfcService.updateSfcStatus(sfc);
            JSONArray vnfResult = deployRes.getJSONArray("vnfDeploys");
            JSONArray routePath = deployRes.getJSONArray("routes");
            //���㷨�����ִ�в���
            try {
                String url = "http://localhost:8089/sfcDeploy";
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("sfcId", sfcId);
                jsonObject.put("results", vnfResult);
                HttpClient httpClient = HttpClients.createDefault();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
                StringEntity stringEntity = new StringEntity(jsonObject.toString(), "utf-8");
                stringEntity.setContentType("UTF-8");
                stringEntity.setContentType("application/json");
                httpPost.setEntity(stringEntity);
                HttpResponse response = httpClient.execute(httpPost);
                int statusCode = response.getStatusLine().getStatusCode();
                if(statusCode == HttpStatus.SC_OK);
            }catch (IOException e){
                e.printStackTrace();
            }
            for (int i = 0; i < routePath.length(); i++){
                controllerSfcService.insertSfc(sfcId);
            }
            List<Integer> cSfcId = controllerSfcService.getControllerSfcId(sfcId);
//            int sfcCount = controllerSfcService.hasRecord() == 0 ? 0 : controllerSfcService.getMaxId();
            for (int i = 0; i < routePath.length(); i++){
                try {
                    String url = "http://localhost:8081/deploy";
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("sfcid", cSfcId.get(i));
                    jsonObject.put("nodes", routePath.getJSONArray(i));
                    HttpClient httpClient = HttpClients.createDefault();
                    HttpPost httpPost = new HttpPost(url);
                    httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
                    StringEntity stringEntity = new StringEntity(jsonObject.toString(), "utf-8");
                    stringEntity.setContentType("UTF-8");
                    stringEntity.setContentType("application/json");
                    httpPost.setEntity(stringEntity);
                    HttpResponse response = httpClient.execute(httpPost);
                    int statusCode = response.getStatusLine().getStatusCode();
                    if(statusCode == HttpStatus.SC_OK);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            /*��������д�����ݿ�*/
            //��¼VNFD��VNF֮��Ķ�Ӧ��ϵ
            for (int i = 0; i < sfcNodeCustoms.size(); i++){
                VnfdRelevance vnfdRelevance = new VnfdRelevance();
                String vnfd = sfcNodeCustoms.get(i).getVnfd();
                vnfdRelevance.setVnfd(vnfd); vnfdRelevance.setStatus("working");
                if (vnfdRelevanceService.hasMatchRecord(vnfd) == 0){
                    vnfdRelevance.setNum(1);
                    vnfdRelevanceService.insertVnfdRelevance(vnfdRelevance);
                }else {
                    int num = vnfdRelevanceService.findSpecifyVnf(vnfd).getNum();
                    vnfdRelevance.setNum(num + 1);
                    vnfdRelevanceService.updateVnfNum(vnfdRelevance);
                }
            }
            //д�����ݿ⣺vnfDeploys��VNF -> ����ڵ㣩
            /**�������ʺ��ڻ���� ����SFC֮����Ӧ������ڵ��ϵ���Դ�Ƿ���٣�*/
            List<VnfDeploy> vnfDeployList = new ArrayList<VnfDeploy>();
            for (int i = 0; i < vnfResult.length(); i++){
                JSONObject resJson = vnfResult.getJSONObject(i);
                VnfDeploy vnfDeploy = new VnfDeploy();
                vnfDeploy.setNodeId(resJson.getString("nodeId")); vnfDeploy.setSfcId(sfcId);
                vnfDeploy.setVnfId(resJson.getInt("vnfId")); vnfDeploy.setVnfd(idAndVnfd.get(resJson.getInt("vnfId")));
                vnfDeployList.add(vnfDeploy);
            }
            VnfDeployCustom vnfDeployCustom = new VnfDeployCustom();
            vnfDeployCustom.setVnfResList(vnfDeployList); vnfDeployService.insertBactch(vnfDeployCustom);
            //д�����ݿ⣺ sfc_Links -> ת��·��
            List<SfcLinkDeploy> sfcLinkDeploys = new ArrayList<SfcLinkDeploy>();
            //�������ʱ�����������������·һ����ģ����������� forѭ�� ������SFC�ı�ǩ���ڲ����SFC�ڲ���·�ı�ǩ
            for (int i = 0; i < linkRes.size(); i++){
                List<String> list = linkRes.get(i);
                String routes = "";
                for (int j = 0; j < list.size(); j++){
                    if (j != list.size() - 1)
                        routes += list.get(j) + ",";
                    else
                        routes += list.get(j);
                }
                SfcLinkDeploy sfcLinkDeploy = new SfcLinkDeploy();
                sfcLinkDeploy.setSfcId(sfcId); sfcLinkDeploy.setResults(routes);
                sfcLinkDeploys.add(sfcLinkDeploy);
            }
            SfcLinkDeployCustom sfcLinkDeployCustom = new SfcLinkDeployCustom();
            sfcLinkDeployCustom.setSfcLinkDeploys(sfcLinkDeploys);
            sfcLinkDeployService.insertLinkResBatch(sfcLinkDeployCustom);

            code.put("status", 1);
        } catch (Exception e){
            e.printStackTrace();
        }
        return code;
    }

    @RequestMapping(value = "/deleteSfc", method = RequestMethod.POST)
    public @ResponseBody int deleteSfc(String[] sfcIdArray){
        if (sfcIdArray == null || sfcIdArray.length == 0) return -1;
        List<Integer> sfcIdList = new ArrayList<Integer>();
        for (int i = 0; i < sfcIdArray.length; i++)
            sfcIdList.add(Integer.valueOf(sfcIdArray[i]));
//        SfcCustom sfcCustom = new SfcCustom();
//        sfcCustom.setSfcIdList(sfcIdList);
        sfcService.deleteSfc(sfcIdList);
        return 1;
    }

    @RequestMapping(value = "/hangUpSfc", method = RequestMethod.POST)
    public @ResponseBody int hangUpSfc(HttpServletRequest request){
        int sfcId = Integer.valueOf(request.getParameter("sfcId"));
//        communicateWithController(sfcId, "hangup");
        String status = sfcService.getSfcStatus(sfcId);
        if (status.equals("working")){
            List<Integer> cSfcId = controllerSfcService.getControllerSfcId(sfcId);
            for (int i = 0; i < cSfcId.size(); i++){
                if (communicateWithController(cSfcId.get(i), "http://localhost:8081/hangup") != 1){
                    return -1;
                }
            }
            Sfc sfc = new Sfc();
            sfc.setSfcId(sfcId); sfc.setStatus("hanging");
            sfcService.updateSfcStatus(sfc);
            return 1;
        }else if (status.equals("hanging")){
            return 2;
        }else {
            return 3;
        }
    }

    @RequestMapping(value = "/wakeUpSfc", method = RequestMethod.POST)
    public @ResponseBody int wakeUpSfc(HttpServletRequest request){
        int sfcId = Integer.valueOf(request.getParameter("sfcId"));
//        communicateWithController(sfcId, "startup");
        String status = sfcService.getSfcStatus(sfcId);
        if (status.equals("hanging")){
            List<Integer> cSfcId = controllerSfcService.getControllerSfcId(sfcId);
            for (int i = 0; i < cSfcId.size(); i++){
                if (communicateWithController(cSfcId.get(i), "http://localhost:8081/startup") != 1){
                    return -1;
                }
            }
            Sfc sfc = new Sfc();
            sfc.setSfcId(sfcId); sfc.setStatus("working");
            sfcService.updateSfcStatus(sfc);
            return 1;
        }else if (status.equals("working")){
            return 2;
        }else {
            return 3;
        }
    }

    @RequestMapping(value = "/terminationSfc", method = RequestMethod.POST)
    public @ResponseBody int terminationSfc(HttpServletRequest request){
        int sfcId = Integer.valueOf(request.getParameter("sfcId"));
        return terminalSFC(sfcId);
    }

    private int terminalSFC(int sfcId) {
        String status = sfcService.getSfcStatus(sfcId);
        if (status.equals("working") || status.equals("hanging")){
            List<Integer> cSfcId = controllerSfcService.getControllerSfcId(sfcId);
            for (int i = 0; i < cSfcId.size(); i++){
                if(communicateWithController(cSfcId.get(i), "http://localhost:8081/delete") != 1){
                    return -1;
                }
            }
            communicateWithController(sfcId,"http://localhost:8089/sfcDemo");
            controllerSfcService.deleteControllerSfcId(sfcId);
            Sfc sfc = new Sfc();
            sfc.setSfcId(sfcId); sfc.setStatus("stateless");
            sfcService.updateSfcStatus(sfc);
            vnfDeployService.deleteDataById(sfcId);
            sfcLinkDeployService.deleteDataById(sfcId);
            List<SfcNodeCustom> sfcNodeCustomList = sfcNodeService.finSfcNodeById(sfcId);
            for (int i = 0; i < sfcNodeCustomList.size(); i++){
                SfcNodeCustom sfcNodeCustom = sfcNodeCustomList.get(i);
                String vnfd = sfcNodeCustom.getVnfd();
                int num = sfcNodeCustom.getCount();
                VnfdRelevance vnfdRelevance = vnfdRelevanceService.findSpecifyVnf(vnfd);
                if(vnfdRelevance != null){
                    int diff = vnfdRelevance.getNum() - num;
                    if(diff == 0){
                        vnfdRelevanceService.deleteVnfByVnfd(vnfd);
                        if(vnfdRelevance.getStatus().equals("deleting")){
                            String path = tempVnfdService.findSwPath(vnfd);
                            tempVnfdService.deleteVnfByVnfd(vnfd);
                            File file = new File(path);
                            if(file.exists())
                                file.delete();
                        }
                    }else{
                        vnfdRelevance.setNum(diff);
                        vnfdRelevanceService.updateVnfNum(vnfdRelevance);
                    }
                }
            }
            return 1; //�ɹ�
        }else if(status.equals("stateless")){
            return 2;  //�Ѵ��ڹ���״̬
        }else {
            return -1; //ʧ��
        }
    }

    /** ��controller ������������������SFC*/
    public int communicateWithController(int sfcId, String path){
        try {
            String url = path;
//            System.out.println(url);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sfcid", sfcId);
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
            StringEntity stringEntity = new StringEntity(jsonObject.toString(), "utf-8");
            stringEntity.setContentType("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == HttpStatus.SC_OK)
                return 1;
        }catch (IOException e){
            e.printStackTrace();
        }
        return -1;
    }

    @RequestMapping(value = "/saveSfc", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> saveSfc(HttpServletRequest request){
        HashMap<String, Object> res = new HashMap<String, Object>();
        String sfcName = request.getParameter("sfcName");
        String userName = request.getParameter("userName");
        String status = request.getParameter("status");
        String sfcNodes = request.getParameter("sfcNodes");
        String sfcLinks = request.getParameter("sfcLinks");
        JSONArray sfcNodeArray = new JSONArray(sfcNodes);
        JSONArray sfcLinkArray = new JSONArray(sfcLinks);
        Sfc sfc = new Sfc();
        sfc.setStatus(status); sfc.setSfcName(sfcName); sfc.setUserName(userName); sfc.setCreateTime(new Date());
        sfcService.addSfc(sfc);
        List<SfcNode> sfcNodeList = new ArrayList<SfcNode>();
        List<SfcLink> sfcLinkList = new ArrayList<SfcLink>();
        for(int i = 0; i < sfcNodeArray.length(); i++){
            JSONObject nodeTem = sfcNodeArray.getJSONObject(i);
            int vnfdId = nodeTem.getInt("vnfdId");
            String vnfd = nodeTem.getString("vnfd");
            String vnfName = nodeTem.getString("vnfName");
            SfcNode sfcNode = new SfcNode();
            sfcNode.setSfcId(sfc.getSfcId()); sfcNode.setVnfd(vnfd);
            sfcNode.setVnfdId(vnfdId); sfcNode.setVnfName(vnfName);
            sfcNodeList.add(sfcNode);
        }
        SfcNodeCustom sfcNodeCustom = new SfcNodeCustom(); sfcNodeCustom.setSfcNodeList(sfcNodeList);
        sfcNodeService.insertNodeBatch(sfcNodeCustom);
        for (int i = 0; i < sfcLinkArray.length(); i++){
            JSONObject linkTem = sfcLinkArray.getJSONObject(i);
            int from = linkTem.getInt("from"); int to = linkTem.getInt("to");
            int flag = linkTem.getInt("branchNum"); int bandwidth = linkTem.getInt("bandwidth");
            int delay = linkTem.getInt("delay"); String fromVnf = linkTem.getString("fromVnf");
            String toVnf = linkTem.getString("toVnf"); int linkId = linkTem.getInt("linkId");
            SfcLink sfcLink = new SfcLink(); sfcLink.setLinkId(linkId);
            sfcLink.setBandwidth(bandwidth); sfcLink.setDelay(delay); sfcLink.setFlag(flag);
            sfcLink.setFrom(from); sfcLink.setTo(to); sfcLink.setSfcId(sfc.getSfcId());
            sfcLink.setFromVnf(fromVnf); sfcLink.setToVnf(toVnf);sfcLinkList.add(sfcLink);
        }
        SfcLinkCustom sfcLinkCustom = new SfcLinkCustom(); sfcLinkCustom.setSfcLinkList(sfcLinkList);
        sfcLinkService.insertLinkBatch(sfcLinkCustom);
        return res;
    }

    @RequestMapping(value = "/dataList.json", method = RequestMethod.GET) //sfcScale.js
    public @ResponseBody Map<String, Object> fetchDataList(HttpServletRequest request){
        HashMap<String, Object> data = new HashMap<String, Object>();
        int sfcId = Integer.valueOf(request.getParameter("sfcId"));
        SfcCustom sfc = sfcService.getSfcById(sfcId);
        SfcMonitor sfcMonitor = sfcMonitorService.getSfcMonitorData(sfcId);
        if (sfcMonitor != null){
            sfc.setAlarmLevel(sfcMonitor.getAlarmLevel()); sfc.setCurrentTime(sfcMonitor.getCurrentTime());
            sfc.setPackageReceive(sfcMonitor.getPackageReceive()); sfc.setPackageDeal(sfcMonitor.getPackageDeal());
            sfc.setThroughput(sfcMonitor.getThroughput());
        }
        List<SfcNode> sfcNodes = sfcNodeService.getSfcNodeById(sfcId);
        List<SfcLink> sfcLinks = sfcLinkService.selectLinkById(sfcId);
        List<VnfDeploy> vnfDeploys = vnfDeployService.selectResBySfcId(sfcId);
        List<SfcLinkDeploy> sfcLinkDeploys = sfcLinkDeployService.selectLinkById(sfcId);
        List<SfcNodeMonitorCustom> sfcNodeMonitorCustoms = sfcNodeMonitorService.getSfcNodeMonitorData(sfc.getSfcName());
        data.put("sfc", sfc);
        data.put("sfcLink", sfcLinks);
        data.put("sfcNode", sfcNodes);
        data.put("vnfDeploy", vnfDeploys);
        data.put("sfcLinkDeploy", sfcLinkDeploys);
        data.put("sfcNodeMonitor", sfcNodeMonitorCustoms);
        return data;
    }

    @RequestMapping(value = "/nodeData.json", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getNodeData(HttpServletRequest request){
        int code = Integer.valueOf(request.getParameter("code"));
        int sfcId = Integer.valueOf(request.getParameter("sfcId"));
        String nodeId = request.getParameter("nodeId");
        HashMap<String, Object> data = new HashMap<String, Object>();
        if (code == 1){
            ForwardingNode forwardingNode = forwardingNodeService.getNodeById(nodeId);
            data.put("code", 1);
            data.put("node", forwardingNode);
        }else if (code == 2){
            FunctionalityNode functionalityNode = functionalityNodeService.getNodeById(nodeId);
            data.put("code", 2);
            data.put("node", functionalityNode);
        }else if (code == 3){
            int vnfId = Integer.valueOf(nodeId);
            SfcNodeCustom sfcNodeCustom = new SfcNodeCustom();
            sfcNodeCustom.setSfcId(sfcId); sfcNodeCustom.setVnfdId(vnfId);
            Vnf vnf = sfcNodeService.selVnfByVnfName(sfcNodeCustom);
            data.put("code", 3);
            data.put("node", vnf);
        }
        return data;
    }

    @RequestMapping(value = "/sfcMonitorData.json", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getSfcMonitorData(HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<String, Object>();
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        String sfcName = request.getParameter("sfcName");
        SfcMonitorCustom sfcMonitorCustom = sfcMonitorService.getSfcInfoByName(sfcName);
        int sfcId = sfcMonitorCustom.getSfcId();
        List<SfcNode> sfcNodes = sfcNodeService.getSfcNodeById(sfcId);
        List<SfcLink> sfcLinks = sfcLinkService.selectLinkById(sfcId);
        hm.put("sfcId", sfcId); hm.put("vnfId", -1);
        List<SfcNodeCustom> sfcNodeCustoms = sfcNodeService.getVnfNodeById(hm);
        map.put("sfcMonitorCustom", sfcMonitorCustom);
        map.put("sfcNode", sfcNodes);
        map.put("sfcLink", sfcLinks);
        map.put("vnfNode", sfcNodeCustoms);
        return map;
    }

    @RequestMapping(value = "/sfcNodeMonitorData.json", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getSfcNodeMonitorData(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String sfcName = request.getParameter("sfcName");
        List<SfcNodeMonitorCustom> sfcNodeMonitorCustoms = sfcNodeMonitorService.getSfcNodeMonitorData(sfcName);
//        int sfcId = sfcNodeMonitorCustoms.get(0).getSfcId();
//        List<SfcNode> sfcNodes = sfcNodeService.getSfcNodeById(sfcId);
//        List<SfcLink> sfcLinks = sfcLinkService.selectLinkById(sfcId);
        map.put("sfcNodeData", sfcNodeMonitorCustoms);
//        map.put("sfcNode", sfcNodes);
//        map.put("sfcLink", sfcLinks);
        return map;
    }

    @RequestMapping(value = "/sfcLinkMonitorData.json", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getSfcLinkMonitorData(HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<String, Object>();
        String sfcName = request.getParameter("sfcName");
        List<SfcLinkMonitorCustom> sfcLinkMonitorCustoms = sfcLinkMonitorService.getSfcLinkMonitorData(sfcName);
        map.put("sfcLinkData", sfcLinkMonitorCustoms);
        return map;
    }

    @RequestMapping(value = "/sfcMonitor.json", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getSfcMonData(){
        HashMap<String, Object> res = new HashMap<String, Object>();
        try{
            JSONArray sfcArray = new JSONArray();
            JSONObject sfcData = new JSONObject();
            JSONArray sfcChildren = new JSONArray();
            sfcData.put("name","SFC");
            List<Integer> sfcIdList = sfcMonitorService.getSfcIdList();
            List<SfcMonitor> sfcMonitors = sfcMonitorService.getRecentSfcStatus();
            for (int i = 0; i < sfcIdList.size(); i++){
                JSONObject sfc = new JSONObject(); JSONArray sfcChild = new JSONArray();
                JSONObject sfcNode = new JSONObject(); JSONObject sfcLink = new JSONObject();
//                JSONArray sfcNodes = new JSONArray(); JSONArray sfcLinks = new JSONArray();
                int sfcId = sfcIdList.get(i);
                Sfc sfcInfo = sfcService.getSfcById(sfcId);
                sfc.put("name",sfcInfo.getSfcName());
//                int vnfNum = sfcNodeService.getVnfSum(sfcId);
//                int linkNum = sfcLinkService.getLinkNum(sfcId);
//                List<SfcNodeMonitor> sfcNodeMonitors = sfcNodeMonitorService.getSfcNodeList(sfcId);
//                List<SfcLinkMonitor> sfcLinkMonitors = sfcLinkMonitorService.getSfcLinkList(sfcId);
//                for (int j = 0; j < vnfNum; j++){
//                    JSONObject nodeId = new JSONObject();
//                    nodeId.put("name", "vnfId:" + j);
//                    sfcNodes.put(nodeId);
//                }
//                for (int j = 0; j < linkNum; j++){
//                    JSONObject linkId = new JSONObject();
//                    linkId.put("name", "linkId:" + j);
//                    sfcLinks.put(linkId);
//                }
                sfcNode.put("name", sfcInfo.getSfcName() + "_node"); sfcLink.put("name", sfcInfo.getSfcName() + "_link");
//                sfcNode.put("children", sfcNodes); sfcLink.put("children", sfcLinks);
                sfcChild.put(sfcNode); sfcChild.put(sfcLink); sfc.put("children", sfcChild);
                sfcChildren.put(sfc);
            }
            sfcData.put("children", sfcChildren); sfcArray.put(sfcData);
            res.put("sfcArray", sfcArray.toString());
            res.put("sfcMonitorInfo", sfcMonitors);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(value = "/vnfMonitorData.json", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getVnfMonitorData(HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<String, Object>();
        int sfcId = Integer.valueOf(request.getParameter("sfcId"));
        int vnfId = Integer.valueOf(request.getParameter("vnfId"));
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        if (beginTime.equals("-1")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = df.format(new Date());
            beginTime = currentDate + " 00:00:00";
            endTime = currentDate + " 23:59:59";
//            beginTime = "2018-04-20 00:00:00";
//            endTime = "2018-04-20 23:59:59";
        }

        HashMap<String, Object> hm = new HashMap<String, Object>();
        hm.put("sfcId", sfcId); hm.put("vnfId", vnfId);
        hm.put("beginTime", beginTime); hm.put("endTime", endTime);
        //����ط����ݿ�鲻�������϶�Ϊ�գ����º��� get0�������Ҫ���ɾ sfc1,��� monitorData ��ģ�����ɵ�Ĭ�Ͼ��� sfc1
        List<SfcNodeCustom> sfcNodeCustoms = sfcNodeService.getVnfNodeById(hm);
        List<SfcNodeMonitorCustom> sfcNodeMonitorCustoms = sfcNodeMonitorService.getNodeMonitorData(hm);
        sfcNodeCustoms.get(0).setAlarmLevel(sfcNodeMonitorCustoms.get(sfcNodeMonitorCustoms.size() - 1).getAlarmLevel());
        //Json�㼶��ͬ������ʾ��VNFArrayΪ��߲㣬����洢��� vnfobject��Object ����VNFchildren
        JSONArray vnfArray = new JSONArray();
        JSONObject vnfObject = new JSONObject();
        JSONArray vnfChildren = new JSONArray();
        JSONObject vnfData = new JSONObject();
        vnfData.put("name", "Name:" + sfcNodeCustoms.get(0).getVnfName()); vnfChildren.put(vnfData);
        vnfData = new JSONObject();
        vnfData.put("name", "Id:" + sfcNodeCustoms.get(0).getVnfdId()); vnfChildren.put(vnfData);
        vnfData = new JSONObject();
        vnfData.put("name", "SfcId:" + sfcNodeCustoms.get(0).getSfcId()); vnfChildren.put(vnfData);
        vnfData = new JSONObject();
        vnfData.put("name", "Cpu:" + sfcNodeCustoms.get(0).getCpu()); vnfChildren.put(vnfData);
        vnfData = new JSONObject();
        vnfData.put("name", "Memory:" + sfcNodeCustoms.get(0).getMemory()); vnfChildren.put(vnfData);
        vnfData = new JSONObject();
        vnfData.put("name", "Storage:" + sfcNodeCustoms.get(0).getStorage()); vnfChildren.put(vnfData);
        vnfData = new JSONObject();
        vnfData.put("name", "Type:" + sfcNodeCustoms.get(0).getVnfProductName()); vnfChildren.put(vnfData);
        vnfData = new JSONObject();
        vnfData.put("name", "AlarmLevel:" + sfcNodeCustoms.get(0).getAlarmLevel()); vnfChildren.put(vnfData);
        vnfObject.put("name","VNF"); vnfObject.put("children", vnfChildren); vnfArray.put(vnfObject);
        vnfObject = new JSONObject(); vnfObject.put("name","Cpu_Util_Rate"); vnfArray.put(vnfObject);
        vnfObject = new JSONObject(); vnfObject.put("name","Memory_Util_Rate"); vnfArray.put(vnfObject);
        vnfObject = new JSONObject(); vnfObject.put("name","Package"); vnfArray.put(vnfObject);
        map.put("vnfData", vnfArray.toString());

        JSONArray cpuUtilArray = new JSONArray();
        JSONArray cpuThreUp = new JSONArray();
        JSONArray cpuThreDown = new JSONArray();
        JSONArray memUtilArray = new JSONArray();
        JSONArray memThreUp = new JSONArray();
        JSONArray memThreDown = new JSONArray();
        JSONArray packageRece = new JSONArray();
        JSONArray packageDeal = new JSONArray();
        JSONArray dateArray = new JSONArray();
        for (int i = 0; i < sfcNodeMonitorCustoms.size(); i++){
            cpuUtilArray.put(sfcNodeMonitorCustoms.get(i).getCpuUtilRate());
            cpuThreUp.put(sfcNodeMonitorCustoms.get(i).getCpuThresholdUp());
            cpuThreDown.put(sfcNodeMonitorCustoms.get(i).getCpuThresholdDown());
            memUtilArray.put(sfcNodeMonitorCustoms.get(i).getMemoryUtilRate());
            memThreUp.put(sfcNodeMonitorCustoms.get(i).getMemoryThresholdUp());
            memThreDown.put(sfcNodeMonitorCustoms.get(i).getMemoryThresholdDown());
            packageRece.put(sfcNodeMonitorCustoms.get(i).getPackageReceive());
            packageDeal.put(sfcNodeMonitorCustoms.get(i).getPackageDeal());
            dateArray.put(sfcNodeMonitorCustoms.get(i).getCurrentTime());
        }
        map.put("cpuUtilArray", cpuUtilArray.toString()); map.put("cpuThreUp", cpuThreUp.toString());
        map.put("cpuThreDown", cpuThreDown.toString()); map.put("memUtilArray", memUtilArray.toString());
        map.put("memThreUp", memThreUp.toString()); map.put("memThreDown", memThreDown.toString());
        map.put("packageRece", packageRece.toString()); map.put("packageDeal", packageDeal.toString());
        map.put("dateArray", dateArray.toString());
        return map;
    }

    @RequestMapping(value = "/linkMonitorData.json", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getLinkMonitorData(HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<String, Object>();
        int sfcId = Integer.valueOf(request.getParameter("sfcId"));
        int linkId = Integer.valueOf(request.getParameter("linkId"));
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        if (beginTime.equals("-1")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = df.format(new Date());
            beginTime = currentDate + " 00:00:00";
            endTime = currentDate + " 23:59:59";
//            beginTime = "2018-04-20 00:00:00";
//            endTime = "2018-04-20 23:59:59";
        }

        HashMap<String, Object> hm = new HashMap<String, Object>();
        hm.put("sfcId", sfcId); hm.put("linkId", linkId);
        hm.put("beginTime", beginTime); hm.put("endTime", endTime);

        SfcLinkCustom sfcLinkCustom = sfcLinkService.getLinkById(hm);
        List<SfcLinkMonitorCustom> sfcLinkMonitorCustoms = sfcLinkMonitorService.getLinkMonitorData(hm);
        sfcLinkCustom.setAlarmLevel(sfcLinkMonitorCustoms.get(sfcLinkMonitorCustoms.size() - 1).getAlarmLevel());
        JSONArray linkArray = new JSONArray();
        JSONObject linkObject = new JSONObject();
        JSONArray linkChildren = new JSONArray();
        JSONObject linkData = new JSONObject();
        linkData.put("name", "SfcId:" + sfcLinkCustom.getSfcId()); linkChildren.put(linkData);
        linkData = new JSONObject();
        linkData.put("name", "Id:" + sfcLinkCustom.getLinkId()); linkChildren.put(linkData);
        linkData = new JSONObject();
        linkData.put("name", "From:" + sfcLinkCustom.getFrom()); linkChildren.put(linkData);
        linkData = new JSONObject();
        linkData.put("name", "To:" + sfcLinkCustom.getTo()); linkChildren.put(linkData);
        linkData = new JSONObject();
        linkData.put("name", "Bandwidth:" + sfcLinkCustom.getBandwidth()); linkChildren.put(linkData);
        linkData = new JSONObject();
        linkData.put("name", "Delay:" + sfcLinkCustom.getDelay()); linkChildren.put(linkData);
        linkData = new JSONObject();
        linkData.put("name", "AlarmLevel:" + sfcLinkCustom.getAlarmLevel()); linkChildren.put(linkData);

        linkObject.put("name","Link"); linkObject.put("children", linkChildren); linkArray.put(linkObject);
        linkObject = new JSONObject(); linkObject.put("name","Bw_Util_Rate"); linkArray.put(linkObject);
        linkObject = new JSONObject(); linkObject.put("name","Delay"); linkArray.put(linkObject);

        map.put("linkData", linkArray.toString());

        JSONArray bwUtilArray = new JSONArray();
        JSONArray bwThreUp = new JSONArray();
        JSONArray bwThreDown = new JSONArray();
        JSONArray delay = new JSONArray();
        JSONArray delayThreshold = new JSONArray();
        JSONArray dateArray = new JSONArray();
        for (int i = 0; i < sfcLinkMonitorCustoms.size(); i++){
            bwUtilArray.put(sfcLinkMonitorCustoms.get(i).getBwUtilRate());
            bwThreUp.put(sfcLinkMonitorCustoms.get(i).getBwThresholdUp());
            bwThreDown.put(sfcLinkMonitorCustoms.get(i).getBwThresholdDown());
            delay.put(sfcLinkMonitorCustoms.get(i).getDelay());
            delayThreshold.put(sfcLinkMonitorCustoms.get(i).getDelayThreshold());
            dateArray.put(sfcLinkMonitorCustoms.get(i).getCurrentTime());
        }
        map.put("bwUtilArray", bwUtilArray.toString()); map.put("bwThreUp", bwThreUp.toString());
        map.put("bwThreDown", bwThreDown.toString()); map.put("delay", delay.toString());
        map.put("delayThreshold", delayThreshold.toString()); map.put("dateArray", dateArray.toString());
        return map;
    }


    @RequestMapping(value = "/sfcScale.json", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> sfcExpansion_ShrinkageData(HttpServletRequest request){

        int sfcId = Integer.valueOf(request.getParameter("sfcId"));
        double delayConstraint = Integer.valueOf((request.getParameter("delayConstraint")));
        HashMap<String, Object> code = new HashMap<String, Object>();
        /*ִ�������ݺ���*/

        /*1.1������ص� Json�ļ�*/
        HashMap<Integer, String> idAndVnfd = new HashMap<Integer, String>();
        try{
            /*���� SFC ����Json�ļ�*/
            JSONObject sfcReq = createSFCReqJson(sfcId, delayConstraint, idAndVnfd);
            if(sfcReq.has("error")){
                if(sfcReq.getInt("error") == -1){
                    code.put("SFCstatus", -1); //SFC�д��ڲ��Ϸ�VNF,��ɾ��!
                    return code;
                }else if(sfcReq.getInt("error") == -2){
                    //������ַ�֧,alert Ŀǰ�����㷨ֻ֧�ֵ����ṹ
                    code.put("AlgorithmStatus", -2);
                    return code;
                }
            }
            /*������������ Json �ļ�*/
            JSONObject phyNet = createPhyNetJsonObject();
        /*1.2 ִ�������㷨����ȡ�µ�ӳ����*/
            int count = algorithmService.getWorkingAlgNum("Scale");
            if(count == 0){ // �������㷨
                code.put("AlgorithmStatus", -1);
                return code;
            }
            Algorithm algorithm = algorithmService.getScaleAlgInfo(); //��ȡ��ǰ�Ĳ����㷨"Scale" ͬ�����͵��㷨ֻ����һ������ working ״̬
            HashMap<String, Object> res = algorithmService.executeAlg(algorithm, phyNet, sfcReq);
            //�㷨ӳ����
            if((Integer) res.get("success") == -1){
                code.put("AlgorithmStatus", -3); //������ʧ��
                return code;
            }
            //List<List<String>> linkRes = (List<List<String>>)res.get("linkResult");
            //Scale�㷨���漰·�ɣ�����ֱ����ԭ�����ݿ��е�·��
            List<List<String>> linkRes =  new ArrayList<List<String>>();
            List<SfcLinkDeploy> sfcLinkDeployList = sfcLinkDeployService.selectLinkById(sfcId);
            for(int i = 0; i < sfcLinkDeployList.size(); i++){
                SfcLinkDeploy sfcLinkDeploy = sfcLinkDeployList.get(i);
                String[] tempLinkDeployResult = sfcLinkDeploy.getResults().split(",");
                linkRes.add(Arrays.asList(tempLinkDeployResult));
            }
            JSONObject deployRes = (JSONObject) res.get("result");
            JSONArray vnfDeploys_JsonArray = deployRes.getJSONArray("vnfDeploys");
            JSONArray routes_JsonArray = deployRes.getJSONArray("routes");


            /*2 ����SFC��ֹ�ӿڣ���ʱ��ֹ sfc,���²���*/
            int terminalFlag = terminalSFC(sfcId);
            if(terminalFlag != 1){ //��ֹʧ�ܣ���ʱ��������
                code.put("terminalFalse",1);
                return code;
            }



            /*3 ���㷨�����ִ�в���*/
            //�� vim ͨ��
            /**����Scale�㷨���ص�JSON�����ExtendVNF_MappedSNodeId�ֶΣ�
             * ���ǲ����VIM������У�����ֱ�ӽ����ݺ� controller �� vim��ͨ�Źص�*/
//            try {
//                String url = "http://localhost:8089/sfcDeploy";
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("sfcId", sfcId);
//                jsonObject.put("results", vnfDeploys_JsonArray);
//                HttpClient httpClient = HttpClients.createDefault();
//                HttpPost httpPost = new HttpPost(url);
//                httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
//                StringEntity stringEntity = new StringEntity(jsonObject.toString(), "utf-8");
//                stringEntity.setContentType("UTF-8");
//                stringEntity.setContentType("application/json");
//                httpPost.setEntity(stringEntity);
//                HttpResponse response = httpClient.execute(httpPost);
//                int statusCode = response.getStatusLine().getStatusCode();
//                if(statusCode == HttpStatus.SC_OK);
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//            for (int i = 0; i < routes_JsonArray.length(); i++){
//                controllerSfcService.insertSfc(sfcId);
//            }
//
//            //�� hello_word ͨ��
//            List<Integer> cSfcId = controllerSfcService.getControllerSfcId(sfcId);
////            int sfcCount = controllerSfcService.hasRecord() == 0 ? 0 : controllerSfcService.getMaxId();
//            for (int i = 0; i < routes_JsonArray.length(); i++){
//                try {
//                    String url = "http://localhost:8081/deploy";
//                    JSONObject jsonObject = new JSONObject();
//                    jsonObject.put("sfcid", cSfcId.get(i));
//                    jsonObject.put("nodes", routes_JsonArray.getJSONArray(i));
//                    HttpClient httpClient = HttpClients.createDefault();
//                    HttpPost httpPost = new HttpPost(url);
//                    httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
//                    StringEntity stringEntity = new StringEntity(jsonObject.toString(), "utf-8");
//                    stringEntity.setContentType("UTF-8");
//                    stringEntity.setContentType("application/json");
//                    httpPost.setEntity(stringEntity);
//                    HttpResponse response = httpClient.execute(httpPost);
//                    int statusCode = response.getStatusLine().getStatusCode();
//                    if(statusCode == HttpStatus.SC_OK);
//                }catch (IOException e){
//                    e.printStackTrace();
//                }
//            }

            /*3.2��������д�����ݿ�*/
            //��¼VNFD��VNF֮��Ķ�Ӧ��ϵ
            List<SfcNodeCustom> sfcNodeCustoms = sfcNodeService.getSfcNode(sfcId); //��ȡSFC�е�VNF
            for (int i = 0; i < sfcNodeCustoms.size(); i++){
                String vnfd = sfcNodeCustoms.get(i).getVnfd();
                VnfdRelevance vnfdRelevance = new VnfdRelevance();
                vnfdRelevance.setVnfd(vnfd);
                vnfdRelevance.setStatus("working");
                if (vnfdRelevanceService.hasMatchRecord(vnfd) == 0){
                    vnfdRelevance.setNum(1);
                    vnfdRelevanceService.insertVnfdRelevance(vnfdRelevance);
                }else {
                    int num = vnfdRelevanceService.findSpecifyVnf(vnfd).getNum();
                    vnfdRelevance.setNum(num + 1);
                    vnfdRelevanceService.updateVnfNum(vnfdRelevance);
                }
            }

            //д�����ݿ⣺vnfDeploys��VNF -> ����ڵ㣩
            /**�������ʺ��ڻ���� ����ֱ��copy һ�� deploy �����Ĵ��룬
             * һ�ڴ����в���֮��δ����������Դ��ʣ����Դ����������ֻ��������*/
            ArrayList<VnfDeploy> vnfDeployList = new ArrayList<VnfDeploy>();
            for (int i = 0; i < vnfDeploys_JsonArray.length(); i++){
                JSONObject vnfJson = vnfDeploys_JsonArray.getJSONObject(i);
                VnfDeploy vnfDeploy = new VnfDeploy();
                vnfDeploy.setNodeId(vnfJson.getString("nodeId"));
                vnfDeploy.setSfcId(sfcId);
                vnfDeploy.setVnfId(vnfJson.getInt("vnfId"));
                vnfDeploy.setVnfd(idAndVnfd.get(vnfJson.getInt("vnfId")));
                //�����ˮƽ���ݣ���Ҫִ�����²���
                if(!vnfJson.getString("ExtendVNF_MappedSNodeId").equals("-1")){
                    VnfDeploy vnf2Deploy = new VnfDeploy();
                    vnf2Deploy.setNodeId(vnfJson.getString("ExtendVNF_MappedSNodeId"));
                    vnf2Deploy.setSfcId(sfcId);
                    vnf2Deploy.setVnfId(vnfJson.getInt("vnfId"));
                    vnf2Deploy.setVnfd(idAndVnfd.get(vnfJson.getInt("vnfId")));
                    vnfDeployList.add(vnf2Deploy);
                }
                vnfDeployList.add(vnfDeploy);
            }
            VnfDeployCustom vnfDeployCustom = new VnfDeployCustom();
            vnfDeployCustom.setVnfResList(vnfDeployList);
            vnfDeployService.insertBactch(vnfDeployCustom);

            //д�����ݿ⣺ sfc_Links -> ת��·��
            List<SfcLinkDeploy> sfcLinkDeploys = new ArrayList<SfcLinkDeploy>();
            for (int i = 0; i < linkRes.size(); i++){
                //�������ʱ����������������ṹ�ģ������з�֧�������������� forѭ�� ������SFC�ṹ��һ������flag�����ڲ����SFC������·��ת��·��
                List<String> list = linkRes.get(i);
                String routes = "";
                for (int j = 0; j < list.size(); j++){
                    if (j != list.size() - 1)
                        routes += list.get(j) + ",";
                    else
                        routes += list.get(j);
                }
                SfcLinkDeploy sfcLinkDeploy = new SfcLinkDeploy();
                sfcLinkDeploy.setSfcId(sfcId); sfcLinkDeploy.setResults(routes);
                sfcLinkDeploys.add(sfcLinkDeploy);
            }
            SfcLinkDeployCustom sfcLinkDeployCustom = new SfcLinkDeployCustom();
            sfcLinkDeployCustom.setSfcLinkDeploys(sfcLinkDeploys);
            sfcLinkDeployService.insertLinkResBatch(sfcLinkDeployCustom);

            code.put("AlgorithmStatus", 1);
        } catch (Exception e){
            e.printStackTrace();
        }

        /*4 ����SFC*/
        Sfc sfc = new Sfc(); sfc.setSfcId(sfcId); sfc.setStatus("working");
        sfcService.updateSfcStatus(sfc);




        return code;
    }

    private JSONObject createPhyNetJsonObject() {
        List<SubLink> subLinks = subLinkService.getTopo();
        JSONObject phyNet = new JSONObject();
        //���switcher��Ŀ�� phyJson
        int switcherNum = forwardingNodeService.getForwardingNodeNum();
        phyNet.put("switcherNum",switcherNum);
        //���������·�� json
        JSONArray topo = new JSONArray();
        for (int i = 0; i < subLinks.size(); i++){
            SubLink subLink = subLinks.get(i);
            JSONObject link = new JSONObject();
            link.put("from",subLink.getFrom()); link.put("to",subLink.getTo());
            link.put("fromDpId",subLink.getFromDpId()); link.put("toDpId",subLink.getToDpId());
            link.put("bandwidth",subLink.getBandwidth()); link.put("delay",subLink.getDelay());
            topo.put(link);
        }
        phyNet.put("topo",topo);
        //��� server�ڵ㵽 josn
        List<FunctionalityNode> functionalityNodes = functionalityNodeService.getNode();
        JSONArray nodes = new JSONArray();
        for (int i = 0; i < functionalityNodes.size(); i++){
            FunctionalityNode functionalityNode = functionalityNodes.get(i);
            JSONObject node = new JSONObject();
            node.put("nodeId", functionalityNode.getNodeId()); node.put("cpu", functionalityNode.getAvaCpu());
            node.put("memory",functionalityNode.getAvaMemory()); node.put("storage",functionalityNode.getAvaStorage());
            nodes.put(node);
        }
        phyNet.put("functionalityNode",nodes);
        return phyNet;
    }

    private JSONObject createSFCReqJson(int sfcId, double delayConstraint, HashMap<Integer, String> idAndVnfd) {
        JSONObject sfcReq = new JSONObject();
        JSONArray vnfArray = new JSONArray();
        JSONArray routesArray = new JSONArray();
        JSONArray route = new JSONArray();
        sfcReq.put("SFCId", sfcId);
        sfcReq.put("delayConstraint",delayConstraint);
        List<SfcNodeCustom> sfcNodeCustoms = sfcNodeService.getSfcNode(sfcId); //��ȡSFC�е�VNF
        int sfcVnfNum = sfcNodeService.getVnfSum(sfcId);
        if(sfcVnfNum != sfcNodeCustoms.size()){
            sfcReq.put("error", -1); //SFC�д��ڲ��Ϸ�VNF,��ɾ��!
            return sfcReq;
        }
        //��� vnf �� json
        List<VnfDeploy> vnfDeployList = vnfDeployService.selectResBySfcId(sfcId);
        for (int i = 0; i < sfcNodeCustoms.size(); i++){
            SfcNodeCustom sfcNodeCustom = sfcNodeCustoms.get(i);
            JSONObject vnf = new JSONObject();
            vnf.put("vnfId", sfcNodeCustom.getVnfdId());
            vnf.put("virtualMemSize", sfcNodeCustom.getMemory());
            vnf.put("numVirtualCpu", sfcNodeCustom.getCpu());
            vnf.put("sizeOfStorage", sfcNodeCustom.getStorage());
            VnfDeploy vnfDeploy = vnfDeployList.get(i);
            if(vnfDeploy.getVnfId() != sfcNodeCustom.getVnfdId()){
                for(int k = 0; k<vnfDeployList.size(); k++){
                    if(vnfDeployList.get(k).getVnfId() == sfcNodeCustom.getVnfdId()){
                        vnfDeploy = vnfDeployList.get(k);
                        break;
                    }
                }
            }
            vnf.put("MappedSNodeId",vnfDeploy.getNodeId());
            vnfArray.put(vnf);
            idAndVnfd.put(sfcNodeCustom.getVnfdId(), sfcNodeCustom.getVnfd());
        }

        //��sfcLink
        List<SfcLink> sfcLinks = sfcLinkService.selectLinkById(sfcId);
        int flag = sfcLinks.get(0).getFlag(); //SFC�еķ�֧
        for(int i = 0; i < sfcLinks.size(); i++){
            SfcLink sfcLink = sfcLinks.get(i); JSONObject link = new JSONObject();
            link.put("from",sfcLink.getFrom()); link.put("to",sfcLink.getTo());
            link.put("bandwidth", sfcLink.getBandwidth()); link.put("delay", sfcLink.getDelay());
            if (sfcLink.getFlag() != flag){ //������ַ�֧,alert Ŀǰ�����㷨ֻ֧�ֵ����ṹ
                sfcReq.put("error", -2);
                return sfcReq;
            }else{
                route.put(link);  //����ԭ���ķ�֧����ֱ�Ӽ���
            }
        }
        if(route.length() != 0) routesArray.put(route);
        sfcReq.put("routes",routesArray);
        sfcReq.put("VNFs", vnfArray);
        return sfcReq;
    }

}
