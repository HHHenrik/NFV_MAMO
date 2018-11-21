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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.PhyLinkMonitorDao;
import zjr.assm.demo.po.PhyLinkMonitor;
import zjr.assm.demo.po.PhyLinkMonitorCustom;

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
public class WritePhyLinkMonitor extends TimerTask {
    private volatile PhyLinkMonitorCustom phyLinkMonitorCustom;
    private ReentrantReadWriteLock phyLinkLock;

    public WritePhyLinkMonitor(){
        this.phyLinkMonitorCustom = new PhyLinkMonitorCustom();
        this.phyLinkLock = new ReentrantReadWriteLock();
    }

    public void run(){
//        while (true){
            phyLinkLock.writeLock().lock();
            try{
                String url = "http://localhost:8089/phyLinkData";
                HttpClient httpClient = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet(url);
                HttpResponse response = httpClient.execute(httpGet);
                int statusCode = response.getStatusLine().getStatusCode();
                if(statusCode == HttpStatus.SC_OK){
                    String phyLinkData = EntityUtils.toString(response.getEntity());
                    JSONArray phyLinkArray = new JSONArray(phyLinkData);
                    System.out.println(phyLinkArray);
                    List<PhyLinkMonitor> phyLinkMonitorList = new ArrayList<PhyLinkMonitor>();
                    for (int i = 0; i < phyLinkArray.length(); i++){
                        JSONObject phyLinkObject = phyLinkArray.getJSONObject(i);
                        PhyLinkMonitor phyLinkMonitor = new PhyLinkMonitor();
                        phyLinkMonitor.setAlarmLevel(phyLinkObject.getInt("alarm_level"));
                        phyLinkMonitor.setBwThreDown(phyLinkObject.getFloat("bw_thre_down"));
                        phyLinkMonitor.setBwThreUp(phyLinkObject.getFloat("bw_thre_up"));
                        phyLinkMonitor.setBwUtilRate(phyLinkObject.getFloat("bw_util_rate"));
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = format.parse(phyLinkObject.getString("current_time"));
                        phyLinkMonitor.setCurrentTime(date);
                        phyLinkMonitor.setDelay(phyLinkObject.getFloat("delay"));
                        phyLinkMonitor.setDelayThreshold(phyLinkObject.getFloat("delay_threshold"));
                        phyLinkMonitor.setFrom(phyLinkObject.getString("from"));
                        phyLinkMonitor.setTo(phyLinkObject.getString("to"));
                        phyLinkMonitorList.add(phyLinkMonitor);
                    }
                    phyLinkMonitorCustom.setPhyLinkMonitorList(phyLinkMonitorList);
                    if (phyLinkMonitorCustom != null){
                        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/ApplicationContext.xml");
                        PhyLinkMonitorDao phyLinkMonitorDao = (PhyLinkMonitorDao) applicationContext.getBean("phyLinkMonitorDao");
                        phyLinkMonitorDao.insertLinkMonitorData(phyLinkMonitorCustom);
                    }
                }
                phyLinkLock.writeLock().unlock();
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
        WritePhyLinkMonitor writePhyLinkMonitor = new WritePhyLinkMonitor();
        Thread thread = new Thread(writePhyLinkMonitor);
        thread.start();
    }
}
