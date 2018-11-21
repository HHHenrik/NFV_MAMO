package zjr.assm.demo.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import zjr.assm.demo.po.Algorithm;
import zjr.assm.demo.service.AlgorithmService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping(value = "/html")
public class StrategyController {
    @Autowired
    private AlgorithmService algorithmService;

    @RequestMapping(value = "/algorithmList.json", method = RequestMethod.GET)
    public @ResponseBody List<Algorithm> getAlgorithmList(){
        return algorithmService.getAllAlgorithm();
    }

    @RequestMapping(value = "/startAlg", method = RequestMethod.POST)
    public @ResponseBody int startAlg(HttpServletRequest request){
        int id = Integer.valueOf(request.getParameter("id"));
        String function = request.getParameter("function");
        if(algorithmService.isWorking(id).equals("working"))
            return 2;
        if(algorithmService.getWorkingAlgNum(function) > 0)
            return 3;
        Algorithm algorithm = new Algorithm();
        algorithm.setId(id); algorithm.setStatus("working");
        algorithmService.updateAlgStatus(algorithm);
        return 1;
    }

    @RequestMapping(value = "/stopAlg", method = RequestMethod.POST)
    public @ResponseBody int stopAlg(HttpServletRequest request){
        int id = Integer.valueOf(request.getParameter("id"));
        if(algorithmService.isWorking(id).equals("stateless"))
            return 2;
        Algorithm algorithm = new Algorithm();
        algorithm.setId(id); algorithm.setStatus("stateless");
        algorithmService.updateAlgStatus(algorithm);
        return 1;
    }

    @RequestMapping(value = "/deleteAlgBatch", method = RequestMethod.POST)
    public @ResponseBody void deleteAlgBatch(HttpServletRequest request){
        String jsonString = request.getParameter("algArray");
        JSONArray json = new JSONArray(jsonString);
        List<Integer> workingId = algorithmService.getWorkingAlgId();
        List<Integer> deletingId = new ArrayList<Integer>();
        List<Integer> unDeletingId = new ArrayList<Integer>();
        for (int i = 0; i < json.length(); i++){
            JSONObject jsonObject = json.getJSONObject(i);
            int id = jsonObject.getInt("id");
            if (workingId.contains(id))
                unDeletingId.add(id);
            else
                deletingId.add(id);
        }
        if(deletingId.size() != 0){
            List<String> pathList = algorithmService.getDeleteAlgJarPath(deletingId);
            algorithmService.deleteAlgBatch(deletingId);
            for (int i = 0; i < pathList.size(); i++){
                String path = pathList.get(i);
                File file = new File(path);
                if(file.exists())
                    file.delete();
            }
        }
    }

    @RequestMapping(value = "/uploadAlgJar", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> uploadAlgJar(HttpServletRequest request) throws Exception{
        HashMap<String, Object> map = new HashMap<String, Object>();
        MultipartHttpServletRequest multipart  = (MultipartHttpServletRequest) request;
        MultipartFile file = multipart.getFile("file");
        String algName = multipart.getParameter("algName");
        String className = multipart.getParameter("className");
        String functionName = multipart.getParameter("functionName");
        String function = multipart.getParameter("function");
        if (!file.isEmpty()){
            String path = "D://Test//";
            String fileName = file.getOriginalFilename();
            if (fileName.endsWith("jar")){
                File filePath = new File(path, fileName);
                if(filePath.exists()){
                    map.put("code", 3);
                    return map;
                }
                if (!filePath.getParentFile().exists())
                    filePath.getParentFile().mkdirs();
                file.transferTo(new File(path + File.separator + fileName));
                Algorithm algorithm = new Algorithm();
                algorithm.setStatus("stateless"); algorithm.setAlgName(algName); algorithm.setCreteTime(new Date());
                algorithm.setClassName(className); algorithm.setFunction(function);
                algorithm.setFunctionName(functionName); algorithm.setPath(path+fileName);
                algorithmService.insertAlg(algorithm);
                map.put("code", 1);
            }else {
                map.put("code", 2);
            }
        }else {
            map.put("code", 4);
        }
        return map;
    }
}
