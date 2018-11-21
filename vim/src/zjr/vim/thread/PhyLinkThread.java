package zjr.vim.thread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PhyLinkThread implements Runnable {
    private String from = "switch1";
    private String to = "server1";
    private int alarmLevel = 0;
    private float bwUtilRate = 60;
    private float bwThreUp = 85;
    private float bwThreDown = 15;
    private int delay = 5;
    private int delayThreshold = 10;
    private JSONArray phyLinkData = new JSONArray();
    public volatile boolean phyLinkFlag = false;
    private ReentrantReadWriteLock phyLinkLock = new ReentrantReadWriteLock();

    @Override
    public void run(){
        while (true){
            phyLinkLock.writeLock().lock();
            try{
                if (phyLinkFlag){
                    phyLinkData = new JSONArray();
                    phyLinkFlag = false;
                }
                int flag = Math.random() > 0.5 ? 1 : 0;
                if (flag == 1){
                    Random random = new Random();
                    int offset = random.nextInt(9);
                    bwUtilRate = 60 + offset * 5;
                }else if (flag == 0){
                    Random random = new Random();
                    int offset = random.nextInt(13);
                    bwUtilRate = 60 - offset * 5;
                }
                if (bwUtilRate >= 85 && bwUtilRate < 90 || bwUtilRate <= 15 && bwUtilRate > 10)
                    alarmLevel = 1;
                else if (bwUtilRate >= 90 && bwUtilRate < 95 || bwUtilRate <= 10 && bwUtilRate > 5)
                    alarmLevel = 2;
                else if (bwUtilRate >= 95 && bwUtilRate < 100 || bwUtilRate >= 5 && bwUtilRate < 0)
                    alarmLevel = 3;
                else if (bwUtilRate == 100 || bwUtilRate == 0)
                    alarmLevel = 4;
                else
                    alarmLevel = 0;
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(date);

                JSONObject phyLink = new JSONObject();
                phyLink.put("from", from);
                phyLink.put("to", to);
                phyLink.put("alarm_level", alarmLevel);
                phyLink.put("bw_util_rate", bwUtilRate);
                phyLink.put("bw_thre_up", bwThreUp);
                phyLink.put("bw_thre_down", bwThreDown);
                phyLink.put("delay", delay);
                phyLink.put("delay_threshold", delayThreshold);
                phyLink.put("current_time", currentTime);
                phyLinkData.put(phyLink);
                phyLinkLock.writeLock().unlock();
                Thread.sleep(30000);
            }catch (JSONException e){
                e.printStackTrace();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public JSONArray getPhyLinkData() {
        return phyLinkData;
    }
}
