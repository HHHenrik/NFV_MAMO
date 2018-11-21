package zjr.assm.demo.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zjr.assm.demo.po.Vnf;
import zjr.assm.demo.service.VnfService;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/html")
public class VnfController {
    @Autowired
    private VnfService vnfService;

    @RequestMapping(value = "/vnfList.json", method = RequestMethod.GET)
    public @ResponseBody List<Vnf> getVnfList(HttpServletRequest request){
        String userName = request.getParameter("userName");
        return vnfService.findVnfByUser(userName);
//        return vnfService.getVnfList();
    }
    @RequestMapping (value = "/addVnf.json", method = RequestMethod.POST)
    public @ResponseBody void addVnf(HttpServletRequest request){
        Vnf selectedVnf = new Vnf();
        selectedVnf.setVnfd(request.getParameter("vnfd"));
        selectedVnf.setVnfName(request.getParameter("vnfName"));
        selectedVnf.setUserName(request.getParameter("userName"));
        selectedVnf.setVnfProductName(request.getParameter("vnfProductName"));
        selectedVnf.setCompany(request.getParameter("company"));
        selectedVnf.setVersion(request.getParameter("version"));
        selectedVnf.setSizeOfStorage(Integer.parseInt(request.getParameter("sizeOfStorage")));
        selectedVnf.setNumVirtualCpu(Integer.parseInt(request.getParameter("numVirtualCpu")));
        selectedVnf.setVirtualMemSize(Integer.parseInt(request.getParameter("virtualMemSize")));
        selectedVnf.setCreateTime(new Date());
        vnfService.addVnf(selectedVnf);
    }

    @RequestMapping(value = "/deleteVnf",method = RequestMethod.POST)
    public @ResponseBody boolean deleteSingleVnf(HttpServletRequest request){
//        HashMap<String,Object> del_status =  new HashMap<>();
        boolean sign;
        String vnfName = request.getParameter("vnfName");
        String vnfd = request.getParameter("vnfd");
        List<String> status = vnfService.selectSfcStatus(vnfd);
        if (!status.contains("working")){
            vnfService.deleteVnfByName(vnfName);
            sign = true;
        }else{
            sign = false;
        }
        return sign;
    }

    @RequestMapping(value = "/deleteQuery",method = RequestMethod.POST)
    public @ResponseBody List<String> deleteQuery(HttpServletRequest request){
        String vnfs = request.getParameter("vnfs");
        JSONArray vnfs_info = new JSONArray(vnfs);
        List<String> del_vnfNames = new ArrayList<String>();
        JSONArray del_vnfs = new JSONArray();
        JSONArray undel_vnfs = new JSONArray();
        for(int i = 0;i <vnfs_info.length();i++){
            JSONObject vnf = vnfs_info.getJSONObject(i);
            List<String> status = vnfService.selectSfcStatus(vnf.getString("vnfd"));
            if(status.contains("working")){
                undel_vnfs.put(vnf);
            }else{
                del_vnfs.put(vnf);
            }
        }
        for(int j = 0;j<del_vnfs.length();j++){
            del_vnfNames.add(del_vnfs.getJSONObject(j).getString("vnfName"));
        }
//        System.out.println(del_vnfNames);
        vnfService.deleteVnfQuery(del_vnfNames);
        return  del_vnfNames;
    }
}
