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
import zjr.assm.demo.dao.SfcLinkMonitorDao;
import zjr.assm.demo.po.SfcLinkMonitor;
import zjr.assm.demo.po.SfcLinkMonitorCustom;

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
public class WriteSfcLinkMonitor extends TimerTask {
    private volatile SfcLinkMonitorCustom sfcLinkMonitorCustom;
    private ReentrantReadWriteLock sfcLock;

    public WriteSfcLinkMonitor(){
        this.sfcLinkMonitorCustom = new SfcLinkMonitorCustom();
        this.sfcLock = new ReentrantReadWriteLock();
    }

    public void run(){
//        while (true){
            sfcLock.writeLock().lock();
            try {
                String url = "http://localhost:8089/sfcLinkData";
                HttpClient httpClient = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet(url);
                HttpResponse response = httpClient.execute(httpGet);
                int statusCode = response.getStatusLine().getStatusCode();
                if(statusCode == HttpStatus.SC_OK){
                    String sfcLinkData = EntityUtils.toString(response.getEntity());
                    JSONArray sfcLinkArray = new JSONArray(sfcLinkData);
                    System.out.println(sfcLinkArray);
                    List<SfcLinkMonitor> sfcLinkMonitorList = new ArrayList<SfcLinkMonitor>();
                    for (int i = 0; i < sfcLinkArray.length(); i++){
                        JSONObject sfcObject = sfcLinkArray.getJSONObject(i);
                        SfcLinkMonitor sfcLinkMonitor = new SfcLinkMonitor();
                        sfcLinkMonitor.setAlarmLevel(sfcObject.getInt("alarm_level"));
                        sfcLinkMonitor.setSfcId(sfcObject.getInt("sfcId"));
                        sfcLinkMonitor.setLinkId(sfcObject.getInt("linkId"));
                        sfcLinkMonitor.setBwUtilRate(sfcObject.getFloat("bw_util_rate"));
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = format.parse(sfcObject.getString("current_time"));
                        sfcLinkMonitor.setCurrentTime(date);
                        sfcLinkMonitor.setBwThresholdUp(sfcObject.getFloat("bw_thre_up"));
                        sfcLinkMonitor.setBwThresholdDown(sfcObject.getFloat("bw_thre_down"));
                        sfcLinkMonitor.setDelay(sfcObject.getFloat("delay"));
                        sfcLinkMonitor.setDelayThreshold(sfcObject.getFloat("delay_threshold"));
                        sfcLinkMonitorList.add(sfcLinkMonitor);
                    }
                    sfcLinkMonitorCustom.setSfcLinkMonitorList(sfcLinkMonitorList);
                    if (sfcLinkMonitorCustom != null){
                        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/ApplicationContext.xml");
                        SfcLinkMonitorDao sfcLinkMonitorDao = (SfcLinkMonitorDao) applicationContext.getBean("sfcLinkMonitorDao");
                        sfcLinkMonitorDao.insertSfcLinkData(sfcLinkMonitorCustom);
                    }
                }
                sfcLock.writeLock().unlock();
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
        WriteSfcLinkMonitor writeSfcLinkMonitor = new WriteSfcLinkMonitor();
        Thread thread = new Thread(writeSfcLinkMonitor);
        thread.start();
    }
}
