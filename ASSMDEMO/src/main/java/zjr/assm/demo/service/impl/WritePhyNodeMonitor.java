package zjr.assm.demo.service.impl;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.PhyNodeMonitorDao;
import zjr.assm.demo.po.PhyNodeMonitor;
import zjr.assm.demo.po.PhyNodeMonitorCustom;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Transactional
public class WritePhyNodeMonitor extends TimerTask {
    private volatile PhyNodeMonitorCustom phyNodeMonitorCustom;
    private ReentrantReadWriteLock phyNodeLock;

    public WritePhyNodeMonitor(){
        this.phyNodeMonitorCustom = new PhyNodeMonitorCustom();
        this.phyNodeLock = new ReentrantReadWriteLock();
    }

    public void run(){
//        while (true){
            phyNodeLock.writeLock().lock();
            try {
                String url = "http://localhost:8089/phyNodeData";
                HttpClient httpClient = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet(url);
                HttpResponse response = httpClient.execute(httpGet);
                int statusCode = response.getStatusLine().getStatusCode();
                if(statusCode == HttpStatus.SC_OK){
                    String phyNodeData = EntityUtils.toString(response.getEntity());
                    JSONArray phyNodeArray = new JSONArray(phyNodeData);
                    System.out.println(phyNodeArray);
                    List<PhyNodeMonitor> phyNodeMonitorList = new ArrayList<PhyNodeMonitor>();
                    for (int i = 0; i < phyNodeArray.length(); i++){
                        JSONObject phyNodeObject = phyNodeArray.getJSONObject(i);
                        PhyNodeMonitor phyNodeMonitor = new PhyNodeMonitor();
                        phyNodeMonitor.setAlarmLevel(phyNodeObject.getInt("alarm_level"));
                        phyNodeMonitor.setCpuThreDown(phyNodeObject.getFloat("cpu_thre_down"));
                        phyNodeMonitor.setCpuThreUp(phyNodeObject.getFloat("cpu_thre_up"));
                        phyNodeMonitor.setCpuUtilRate(phyNodeObject.getFloat("cpu_util_rate"));
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = format.parse(phyNodeObject.getString("current_time"));
                        phyNodeMonitor.setCurrentTime(date);
                        phyNodeMonitor.setMemoryThreDown(phyNodeObject.getFloat("memory_thre_down"));
                        phyNodeMonitor.setMemoryThreUp(phyNodeObject.getFloat("memory_thre_up"));
                        phyNodeMonitor.setMemoryUtilRate(phyNodeObject.getFloat("memory_util_rate"));
                        phyNodeMonitor.setNodeId(phyNodeObject.getString("nodeId"));
                        phyNodeMonitor.setStorageThreDown(phyNodeObject.getFloat("storage_thre_down"));
                        phyNodeMonitor.setStorageThreUp(phyNodeObject.getFloat("storage_thre_up"));
                        phyNodeMonitor.setStorageUtilRate(phyNodeObject.getFloat("storage_util_rate"));
                        phyNodeMonitorList.add(phyNodeMonitor);
                    }
                    phyNodeMonitorCustom.setPhyNodeMonitorList(phyNodeMonitorList);
                    System.out.println(phyNodeMonitorCustom.getPhyNodeMonitorList().get(0).getCurrentTime());
                    if (phyNodeMonitorCustom != null){
                        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/ApplicationContext.xml");
                        PhyNodeMonitorDao phyNodeMonitorDao = (PhyNodeMonitorDao)applicationContext.getBean("phyNodeMonitorDao");
                        phyNodeMonitorDao.insertNodeMonitorData(phyNodeMonitorCustom);
                    }
                }
                phyNodeLock.writeLock().unlock();
//                Thread.sleep(300000);
            }
//            catch (InterruptedException e){
//                e.printStackTrace();
//            }
            catch (IOException e){
                e.printStackTrace();
            }catch (ParseException e){
                e.printStackTrace();
            }
//        }
    }

    public static void main(String args[]){
        WritePhyNodeMonitor writePhyNodeMonitor = new WritePhyNodeMonitor();
        Thread thread = new Thread(writePhyNodeMonitor);
        thread.start();
    }
}
