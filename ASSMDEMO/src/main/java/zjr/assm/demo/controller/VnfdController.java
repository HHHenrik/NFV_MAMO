package zjr.assm.demo.controller;

import net.lingala.zip4j.exception.ZipException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import zjr.assm.demo.po.*;
import zjr.assm.demo.service.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

@Controller
@RequestMapping("/html")
public class VnfdController {
    @Autowired
    private VnfdService vnfdService;

    @Autowired
    private VimService vimService;

    @Autowired
    private VnfdRelevanceService vnfdRelevanceService;

    @Autowired
    private TempVnfdService tempVnfdService;

    @RequestMapping(value = "/vnfdList.json", method = RequestMethod.GET)
    public @ResponseBody List<Vnfd> getVnfdList(){
//        System.out.println(vnfdService.getVnfdList().get(0).getNumVirtualCpu());
        return vnfdService.getVnfdList();
    }

    @RequestMapping(value = "/deleteVnfd", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> deleteVnfd(HttpServletRequest request){
        Map<String, Object> res = new HashMap<String, Object>();
        String vnfd = request.getParameter("vnfd");
        String company = request.getParameter("company");

        Vnfd vnf = new Vnfd();
        vnf.setVnfd(vnfd); vnf.setCompany(company);
        delete(vnfd, vnf);
//        VnfdRelevance vnfdRelevance = vnfdRelevanceService.findSpecifyVnf(vnfd);
//        Vnfd temVnf = vnfdService.findVnfByVnfd(vnf);
//        if(vnfdRelevance != null && vnfdRelevance.getNum() != 0){//更改VNFD_RELEVANCE表状态,复制VNFD数据,通知VIM
//            vnfdRelevance.setNum(-1);
//            vnfdRelevance.setStatus("deleting");
//            //复制数据至tempVnfd表
//            tempVnfdService.addTempVnfd(temVnf);
//            vnfdRelevanceService.updateVnfNum(vnfdRelevance);
//        }else{//从VNFD包获取VNFD地址,删除镜像,删除VNFD信息
//            String path = "";
//            if(temVnf != null)
//                path = temVnf.getSwImageDesc();
//            File file = new File(path);
//            if(file.exists())
//                file.delete();
//        }
        vnfdService.deleteVnfd(vnf);
        List<String> vnfdList = new ArrayList<String>();
        vnfdList.add(vnfd);
        vimDelete(vnfdList);
        //用户使用的VNFD在使用之前先进行一次状态检验，若有已经删除的VNFD，则通知用户该条SFC或该VNF不可用
        return res;
    }

    @RequestMapping(value = "/deleteVnfdBatch", method = RequestMethod.POST)
    public @ResponseBody void deleteVnfdBatch(HttpServletRequest request){
        String jsonString = request.getParameter("vnfdArray");
        JSONArray array = new JSONArray(jsonString);
        List<String> vnfdList = new ArrayList<String>();
        for(int i = 0; i < array.length(); i++){
            vnfdList.add(array.getJSONObject(i).getString("vnfd"));
            Vnfd vnf = new Vnfd();
            String vnfd = array.getJSONObject(i).getString("vnfd");
            String company = array.getJSONObject(i).getString("company");
            vnf.setCompany(company);
            vnf.setVnfd(vnfd);
            delete(vnfd, vnf);
        }
        VnfdCustom vnfdCustom = new VnfdCustom();
        vnfdCustom.setVnfdList(vnfdList);
        vnfdService.deleteVnfdBatch(vnfdCustom);
        vimDelete(vnfdList);
    }

    public int delete(String vnfd, Vnfd vnf){
        VnfdRelevance vnfdRelevance = vnfdRelevanceService.findSpecifyVnf(vnfd);
        Vnfd temVnf = vnfdService.findVnfByVnfd(vnf);
        if(vnfdRelevance != null && vnfdRelevance.getNum() != 0){//更改VNFD_RELEVANCE表状态,复制VNFD数据,通知VIM
            vnfdRelevance.setNum(-1);
            vnfdRelevance.setStatus("deleting");
            //复制数据至tempVnfd表
            tempVnfdService.addTempVnfd(temVnf);
            vnfdRelevanceService.updateVnfNum(vnfdRelevance);
            return 1;
        }else{//从VNFD包获取VNFD地址,删除镜像,删除VNFD信息
            String path = "";
            if(temVnf != null)
                path = temVnf.getSwImageDesc();
            File file = new File(path);
            if(file.exists())
                file.delete();
            return 2;
        }
    }

    public int vimDelete(List<String> vnfdList){
        try{
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://127.0.0.1:8089/delDemo");
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < vnfdList.size(); i++){
                JSONObject json = new JSONObject();
                json.put("vnfd", vnfdList.get(i));
                json.put("status", "deleting");
                jsonArray.put(json);
            }
            httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
            StringEntity stringEntity = new StringEntity(jsonArray.toString(), "utf-8");
            stringEntity.setContentType("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
                return 1;
            else
                return -1;
        }catch (IOException e){
            e.printStackTrace();
        }
        return 1;
    }

    @RequestMapping(value = "/uploadVnfd", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> uploadVnf(@RequestParam("file")MultipartFile file) throws Exception {
        Map<String, Object> code = new HashMap<String, Object>();
        if (!file.isEmpty()) {
            String path = "D://fileUpload//";
            String fileName = file.getOriginalFilename();
            if (fileName.endsWith("zip")) {
                File filePath = new File(path, fileName);
                if(filePath.exists()){
                    code.put("code",'4');
                    return code;
                }
                if (!filePath.getParentFile().exists())
                    filePath.getParentFile().mkdirs();
                file.transferTo(new File(path + File.separator + fileName));
                code.put("code",'1');
                code.put("path", path + fileName);
                ZipFile zipFile = new ZipFile(path + File.separator + fileName);
                InputStream inputStream = new BufferedInputStream(new FileInputStream(path + File.separator + fileName));
                ZipInputStream zin = new ZipInputStream(inputStream);
                ZipEntry zipEntry;
                int isoFlag = 0, jsonFlag = 0;
                while ((zipEntry = zin.getNextEntry()) != null){
                    if(!zipEntry.isDirectory()){
                        if (zipEntry.getName().endsWith("iso"))
                            isoFlag = 1;
                        if (zipEntry.getName().endsWith("json"))
                            jsonFlag = 1;
                    }
                }
                zin.close();
                inputStream.close();
                zipFile.close();

                if(isoFlag == 1 && jsonFlag == 1){
                    zipFile = new ZipFile(path + File.separator + fileName);
                    inputStream = new BufferedInputStream(new FileInputStream(path + File.separator + fileName));
                    zin = new ZipInputStream(inputStream);
                    while ((zipEntry = zin.getNextEntry()) != null) {
                        if (!zipEntry.isDirectory()) {
                            System.out.println("file: " + zipEntry.getName() + "\nsize:" + zipEntry.getSize() + " bytes");
                            if (zipEntry.getName().endsWith("json")) {
                                BufferedReader reader = new BufferedReader(new InputStreamReader(zipFile.getInputStream(zipEntry), "utf-8"));
                                try {
                                    String line;
                                    String jsonString = "";
                                    while ((line = reader.readLine()) != null) {
                                        jsonString += line;
                                    }
                                    System.out.println(jsonString);
                                    reader.close();
                                   // jsonString = '{' + jsonString ;
                                    JSONObject vnfdJson = new JSONObject(jsonString);
                                    Vnfd vnfd = new Vnfd();
                                    vnfd.setVnfd(vnfdJson.getString("vnfd"));
                                    vnfd.setCompany(vnfdJson.getString("company"));
                                    vnfd.setVersion(vnfdJson.getString("version"));
                                    vnfd.setVnfProductName(vnfdJson.getString("vnfProductName"));
                                    vnfd.setVirtualMemSize(vnfdJson.getInt("virtualMemSize"));
                                    vnfd.setCpuArchiecture(vnfdJson.getString("cpuArchiecture"));
                                    vnfd.setNumVirtualCpu(vnfdJson.getInt("numVirtualCpu"));
                                    vnfd.setVirtualCpuClock(vnfdJson.getFloat("virtualCpuClock"));
                                    vnfd.setTypeOfStorage(vnfdJson.getString("typeOfStorage"));
                                    vnfd.setSizeOfStorage(vnfdJson.getInt("sizeOfStorage"));
                                    vnfd.setVnfSoftwareVersion(vnfdJson.getString("vnfSoftwareVersion"));
                                    vnfd.setVirtualEnviroment(vnfdJson.getString("virtualEnviroment"));
                                    vnfd.setStatus(vnfdJson.getString("status"));
                                    vnfd.setSwImageDesc(path + fileName);

                                    vnfdService.addVnfd(vnfd);
                                    code.put("vnfd",vnfdJson.getString("vnfd"));
                                    code.put("typeOfStorage", vnfdJson.getString("typeOfStorage"));
                                    code.put("virtualEnviroment", vnfdJson.getString("virtualEnviroment"));
                                    return code;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                     }
                    zin.close();
                    inputStream.close();
                    zipFile.close();
                }else if (isoFlag == 0){
                    code.put("code",5);
                    File delFile = new File(path + File.separator + fileName);
                    delFile.delete();
                }else if (jsonFlag == 0){
                    code.put("code",6);
                    File delFile = new File(path + File.separator + fileName);
                    delFile.delete();
                }
            } else {
                code.put("code", '2');
            }
        }else {
            code.put("code", '3');
        }
        return code;
    }

    @RequestMapping(value = "/unZip", method = RequestMethod.POST)
    public @ResponseBody void unZip(HttpServletRequest request) throws ZipException{
       try{
           HttpClient httpClient = HttpClients.createDefault();
           HttpPost httpPost = new HttpPost("http://localhost:8089/fileDemo");
           MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

           String fileName = "";
           String path = request.getParameter("path");
           String vnfd = request.getParameter("vnfd");
           String virtualEnviroment = request.getParameter("virtualEnviroment");
           String typeOfStorage = request.getParameter("typeOfStorage");
           Vim vim = new Vim();
           vim.setTypeOfStorage(typeOfStorage); vim.setVirtualEnvironment(virtualEnviroment);
           ZipFile zipFile = new ZipFile(path);
           InputStream inputStream = new BufferedInputStream(new FileInputStream(path));
           ZipInputStream zin = new ZipInputStream(inputStream);
           ZipEntry zipEntry;
           while ((zipEntry = zin.getNextEntry()) != null){
               if(zipEntry.getName().endsWith("iso")){
                   fileName = zipEntry.getName();
                   break;
               }
           }
           zin.close();
           inputStream.close();
           zipFile.close();
           String newPath = path.split("\\.")[0];
           net.lingala.zip4j.core.ZipFile zipFile1 = new net.lingala.zip4j.core.ZipFile(path);
           zipFile1.extractFile(fileName, newPath);

           File directory = new File(newPath);
           newPath += "//";
           File file = new File(newPath + fileName);

           List<String> serverList = vimService.findSpecifyVim(vim);
           for (int i = 0; i < serverList.size(); i++){
               String server = serverList.get(i);
               multipartEntityBuilder.addBinaryBody("iso",file);
               multipartEntityBuilder.addTextBody("vnfd",vnfd);
               multipartEntityBuilder.addTextBody("Server",server);
               HttpEntity httpEntity = multipartEntityBuilder.build();
               httpPost.setEntity(httpEntity);
               httpClient.execute(httpPost);
           }

           if(directory.exists()){
               File[] files = directory.listFiles();
               for(int i = 0; i < files.length; i++){
                   files[i].delete();
               }
               directory.delete();
           }
       }catch (IOException e){
           e.printStackTrace();
       }
    }

//    @RequestMapping(value = "/updateSfcStatus", method = RequestMethod.POST)
//    public @ResponseBody void updateSfcStatus(HttpServletRequest request) throws IOException{
//        request.setCharacterEncoding("UTF-8");
//        InputStream inputStream = request.getInputStream();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//        String s = reader.readLine();
//        try{
//            JSONObject jsonObject = new JSONObject(s);
//            int sfcId = jsonObject.getInt("sfcId");
//            System.out.println(sfcId);
//            List<SfcNodeCustom> sfcNodeCustomList = sfcNodeService.finSfcNodeById(sfcId);
//            for (int i = 0; i < sfcNodeCustomList.size(); i++){
//                SfcNodeCustom sfcNodeCustom = sfcNodeCustomList.get(i);
//                String vnfd = sfcNodeCustom.getVnfd();
//                int num = sfcNodeCustom.getCount();
//                VnfdRelevance vnfdRelevance = vnfdRelevanceService.findSpecifyVnf(vnfd);
//                if(vnfdRelevance != null){
//                    int diff = vnfdRelevance.getNum() - num;
//                    if(diff == 0){
//                        vnfdRelevanceService.deleteVnfByVnfd(vnfd);
//                        if(vnfdRelevance.getStatus().equals("deleting")){
//                            String path = tempVnfdService.findSwPath(vnfd);
//                            tempVnfdService.deleteVnfByVnfd(vnfd);
//                            File file = new File(path);
//                            if(file.exists())
//                                file.delete();
//                        }
//                    }else{
//                        vnfdRelevance.setNum(diff);
//                        vnfdRelevanceService.updateVnfNum(vnfdRelevance);
//                    }
//                }
//            }
//        }catch (JSONException e){
//            e.printStackTrace();
//        }
//    }

    @RequestMapping(value = "/startVnfd", method = RequestMethod.POST)
    public @ResponseBody int startVnfd(HttpServletRequest request){
        String vnfd = request.getParameter("vnfd");
        //String function = request.getParameter("function");
        if(vnfdService.isWorking(vnfd).equals("working"))
            return 2;
        Vnfd vnfd1 = new Vnfd();
        vnfd1.setVnfd(vnfd); vnfd1.setStatus("working");
        vnfdService.updateVnfdStatus(vnfd1);
        return 1;
    }

    @RequestMapping(value = "/stopVnfd", method = RequestMethod.POST)
    public @ResponseBody int stopVnfd(HttpServletRequest request){
        String vnfdString = request.getParameter("vnfd");
        if(vnfdService.isWorking(vnfdString).equals("stateless"))
            return 2;
        Vnfd vnfd = new Vnfd();
        vnfd.setVnfd(vnfdString); vnfd.setStatus("stateless");
        vnfdService.updateVnfdStatus(vnfd);
        return 1;
    }
}
