package zjr.vim.thread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SfcLinkThread implements Runnable {
    private int sfcId = 1;
    private int linkId = 0;
    private int alarmLevel = 0;
    private float bwUtilRate = 70;
    private float bwThreUp = 85;
    private float bwThreDown = 15;
    private int delay = 5;
    private int delayThreshold = 10;

    private JSONArray sfcLinkData = new JSONArray();
    public volatile boolean sfcLinkFlag = false;
    private ReentrantReadWriteLock sfcLinkLock = new ReentrantReadWriteLock();

    @Override
    public void run(){
        while (true){
            sfcLinkLock.writeLock().lock();
            try{
                if (sfcLinkFlag){
                    sfcLinkData = new JSONArray();
                    sfcLinkFlag = false;
                }
                int flag = Math.random() > 0.5 ? 1 : 0;
                if (flag == 1){
                    Random random = new Random();
                    int offset = random.nextInt(7);
                    bwUtilRate = 70 + offset * 5;
                }else if (flag == 0){
                    Random random = new Random();
                    int offset = random.nextInt(15);
                    bwUtilRate = 70 - offset * 5;
                }
                if (bwUtilRate > 10 && bwUtilRate < 85){
                    alarmLevel = 0;
                }
                else if (bwUtilRate >= 85 && bwUtilRate < 90 || bwUtilRate <= 15 && bwUtilRate > 10)
                    alarmLevel = 1;
                else if (bwUtilRate >= 90 && bwUtilRate < 95 || bwUtilRate <= 10 && bwUtilRate > 5)
                    alarmLevel = 2;
                else if (bwUtilRate >= 95 && bwUtilRate < 100 || bwUtilRate >= 5 && bwUtilRate < 0)
                    alarmLevel = 3;
                else if (bwUtilRate == 100 || bwUtilRate == 0)
                    alarmLevel = 4;

                JSONObject sfcLink = new JSONObject();
                sfcLink.put("sfcId", sfcId);
                sfcLink.put("linkId", linkId);
                sfcLink.put("bw_util_rate", bwUtilRate);
                sfcLink.put("bw_thre_up", bwThreUp);
                sfcLink.put("bw_thre_down", bwThreDown);
                sfcLink.put("delay", delay);
                sfcLink.put("delay_threshold", delayThreshold);
                sfcLink.put("alarm_level", alarmLevel);
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(date);
                sfcLink.put("current_time", currentTime);
                sfcLinkData.put(sfcLink);

                sfcLinkLock.writeLock().unlock();
                Thread.sleep(30000);
            }catch (JSONException e){
                e.printStackTrace();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public JSONArray getSfcLinkData() {
        return sfcLinkData;
    }
}
