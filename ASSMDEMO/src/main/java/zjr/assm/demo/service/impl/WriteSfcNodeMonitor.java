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
import zjr.assm.demo.dao.SfcNodeMonitorDao;
import zjr.assm.demo.po.SfcNodeMonitor;
import zjr.assm.demo.po.SfcNodeMonitorCustom;

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
public class WriteSfcNodeMonitor extends TimerTask{
    private volatile SfcNodeMonitorCustom sfcNodeMonitorCustom;
    private ReentrantReadWriteLock sfcNodeLock;

    public WriteSfcNodeMonitor(){
        this.sfcNodeMonitorCustom = new SfcNodeMonitorCustom();
        this.sfcNodeLock = new ReentrantReadWriteLock();
    }

    public void run(){
//        while (true){
            sfcNodeLock.writeLock().lock();
            try {
                String url = "http://localhost:8089/sfcNodeData";
                HttpClient httpClient = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet(url);
                HttpResponse response = httpClient.execute(httpGet);
                int statusCode = response.getStatusLine().getStatusCode();
                if(statusCode == HttpStatus.SC_OK){
                    String sfcNodeData = EntityUtils.toString(response.getEntity());
                    JSONArray sfcNodeArray = new JSONArray(sfcNodeData);
                    System.out.println(sfcNodeArray);
                    List<SfcNodeMonitor> sfcNodeMonitorList = new ArrayList<SfcNodeMonitor>();
                    for (int i = 0; i < sfcNodeArray.length(); i++){
                        JSONObject sfcObject = sfcNodeArray.getJSONObject(i);
                        SfcNodeMonitor sfcNodeMonitor = new SfcNodeMonitor();
                        sfcNodeMonitor.setAlarmLevel(sfcObject.getInt("alarm_level"));
                        sfcNodeMonitor.setSfcId(sfcObject.getInt("sfcId"));
                        sfcNodeMonitor.setPackageReceive(sfcObject.getFloat("package_receive"));
                        sfcNodeMonitor.setPackageDeal(sfcObject.getFloat("package_deal"));
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = format.parse(sfcObject.getString("current_time"));
                        sfcNodeMonitor.setCurrentTime(date);
                        sfcNodeMonitor.setVnfId(sfcObject.getInt("vnfId"));
                        sfcNodeMonitor.setVnfd(sfcObject.getString("vnfd"));
                        sfcNodeMonitor.setCpuUtilRate(sfcObject.getFloat("cpu_util_rate"));
                        sfcNodeMonitor.setMemoryUtilRate(sfcObject.getFloat("memory_util_rate"));
                        sfcNodeMonitor.setStorageUtilRate(sfcObject.getFloat("storage_util_rate"));
                        sfcNodeMonitor.setCpuThresholdUp(sfcObject.getFloat("cpu_thre_up"));
                        sfcNodeMonitor.setCpuThresholdDown(sfcObject.getFloat("cpu_thre_down"));
                        sfcNodeMonitor.setMemoryThresholdUp(sfcObject.getFloat("memory_thre_up"));
                        sfcNodeMonitor.setMemoryThresholdDown(sfcObject.getFloat("memory_thre_down"));
                        sfcNodeMonitor.setStorageThresholdDown(sfcObject.getFloat("storage_thre_down"));
                        sfcNodeMonitor.setStorageThresholdUp(sfcObject.getFloat("storage_thre_up"));
                        sfcNodeMonitorList.add(sfcNodeMonitor);
                    }
                    sfcNodeMonitorCustom.setSfcNodeMonitorList(sfcNodeMonitorList);
                    if (sfcNodeMonitorCustom != null){
                        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/ApplicationContext.xml");
                        SfcNodeMonitorDao sfcNodeMonitorDao = (SfcNodeMonitorDao) applicationContext.getBean("sfcNodeMonitorDao");
                        sfcNodeMonitorDao.insertSfcNodeData(sfcNodeMonitorCustom);
                    }
                }
                sfcNodeLock.writeLock().unlock();
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
        WriteSfcNodeMonitor writeSfcNodeMonitor = new WriteSfcNodeMonitor();
        Thread thread = new Thread(writeSfcNodeMonitor);
        thread.start();
    }
}
