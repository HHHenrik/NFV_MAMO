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
import zjr.assm.demo.dao.SfcMonitorDao;
import zjr.assm.demo.po.SfcMonitor;
import zjr.assm.demo.po.SfcMonitorCustom;

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
public class WriteSfcMonitor extends TimerTask {
    private volatile SfcMonitorCustom sfcMonitorCustom;
    private ReentrantReadWriteLock sfcLock;

    public WriteSfcMonitor(){
        this.sfcMonitorCustom = new SfcMonitorCustom();
        this.sfcLock = new ReentrantReadWriteLock();
    }
    public void run(){
//        while (true){
            sfcLock.writeLock().lock();
            try {
                String url = "http://localhost:8089/sfcData";
                HttpClient httpClient = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet(url);
                HttpResponse response = httpClient.execute(httpGet);
                int statusCode = response.getStatusLine().getStatusCode();
                if(statusCode == HttpStatus.SC_OK){
                    String sfcData = EntityUtils.toString(response.getEntity());
                    JSONArray sfcArray = new JSONArray(sfcData);
                    System.out.println(sfcArray);
                    List<SfcMonitor> sfcMonitorList = new ArrayList<SfcMonitor>();
                    for (int i = 0; i < sfcArray.length(); i++){
                        JSONObject sfcObject = sfcArray.getJSONObject(i);
                        SfcMonitor sfcMonitor = new SfcMonitor();
                        sfcMonitor.setAlarmLevel(sfcObject.getInt("alarm_level"));
                        sfcMonitor.setSfcId(sfcObject.getInt("sfcId"));
                        sfcMonitor.setPackageReceive(sfcObject.getFloat("package_receive"));
                        sfcMonitor.setPackageLoss(sfcObject.getFloat("package_loss"));
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = format.parse(sfcObject.getString("current_time"));
                        sfcMonitor.setCurrentTime(date);
                        sfcMonitor.setPackageDeal(sfcObject.getFloat("package_deal"));
                        sfcMonitor.setThroughput(sfcObject.getFloat("throughput"));
                        sfcMonitorList.add(sfcMonitor);
                    }
                    sfcMonitorCustom.setSfcMonitorList(sfcMonitorList);
                    if (sfcMonitorCustom != null){
                        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/ApplicationContext.xml");
                        SfcMonitorDao sfcMonitorDao = (SfcMonitorDao) applicationContext.getBean("sfcMonitorDao");
                        sfcMonitorDao.insertSfcData(sfcMonitorCustom);
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
        WriteSfcMonitor writeSfcMonitor = new WriteSfcMonitor();
        Thread thread = new Thread(writeSfcMonitor);
        thread.start();
    }
}
