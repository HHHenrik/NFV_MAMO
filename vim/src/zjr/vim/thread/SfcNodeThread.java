package zjr.vim.thread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SfcNodeThread implements Runnable {
    private JSONArray sfcNodeData = new JSONArray();
    public volatile boolean sfcNodeFlag = false;
    private ReentrantReadWriteLock sfcNodeLock = new ReentrantReadWriteLock();

    private int sfcId = 1;
    private int vnfId = 0;
    private String vnfd = "3bb03f33cb3797c4ab123b055e1359c5";
    private float cpuUtilRate = 60;
    private float memoryUtilRate = 70;
    private float storageUtilRate = 80;
    private float cpuThreUp = 90;
    private float cpuThreDown = 10;
    private float memoryThreUp = 85;
    private float memoryThreDown = 15;
    private float storageThreUp = 90;
    private float storageThreDown = 5;
    private int packageReceive = 70;
    private int packageDeal = 70;
    private int alarmLevel = 0;

    @Override
    public void run() {
        while (true){
            sfcNodeLock.writeLock().lock();
            try{
                if (sfcNodeFlag){
                    sfcNodeData = new JSONArray();
                    sfcNodeFlag = false;
                }
                JSONObject phyNode = new JSONObject();
                int flag = Math.random() > 0.5 ? 1 : 0;
                if (flag == 1){
                    Random random = new Random();
                    int offset = random.nextInt(9);
                    cpuUtilRate = 60 + offset * 5;
                    offset = random.nextInt(7);
                    memoryUtilRate = 70 + offset * 5;
                    offset = random.nextInt(5);
                    storageUtilRate = 80 + offset * 5;
                    offset = random.nextInt(30) + 30;
                    packageReceive = 70 + offset;
                    offset = random.nextInt(10);
                    packageDeal = packageReceive - offset;
                }else if (flag == 0){
                    Random random = new Random();
                    int offset = random.nextInt(13);
                    cpuUtilRate = 60 - offset * 5;
                    offset = random.nextInt(15);
                    memoryUtilRate = 70 - offset * 5;
                    offset = random.nextInt(17);
                    storageUtilRate = 80 - offset * 5;
                    offset = random.nextInt(30) + 30;
                    packageReceive = 70 - offset;
                    offset = random.nextInt(10);
                    packageDeal = packageReceive - offset;
                }
                if (cpuUtilRate > 10 && cpuUtilRate < 90)
                    alarmLevel = 0;
                else if (cpuUtilRate >= 90 && cpuUtilRate < 95 || cpuUtilRate <= 10 && cpuUtilRate > 5)
                    alarmLevel = 1;
                else if (cpuUtilRate >= 95 && cpuUtilRate < 100 || cpuUtilRate <= 5 && cpuUtilRate > 0)
                    alarmLevel = 2;
                else if (alarmLevel == 100 || alarmLevel == 0)
                    alarmLevel = 3;
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(date);
                JSONObject sfcNode = new JSONObject();
                sfcNode.put("sfcId", sfcId);
                sfcNode.put("vnfId", vnfId);
                sfcNode.put("vnfd", vnfd);
                sfcNode.put("cpu_util_rate", cpuUtilRate);
                sfcNode.put("memory_util_rate", memoryUtilRate);
                sfcNode.put("storage_util_rate", storageUtilRate);
                sfcNode.put("cpu_thre_up", cpuThreUp);
                sfcNode.put("cpu_thre_down", cpuThreDown);
                sfcNode.put("memory_thre_up", memoryThreUp);
                sfcNode.put("memory_thre_down", memoryThreDown);
                sfcNode.put("storage_thre_up", storageThreUp);
                sfcNode.put("storage_thre_down", storageThreDown);
                sfcNode.put("package_receive", packageReceive);
                sfcNode.put("package_deal", packageDeal);
                sfcNode.put("alarm_level", alarmLevel);
                sfcNode.put("current_time", currentTime);
                sfcNodeData.put(sfcNode);

                sfcNodeLock.writeLock().unlock();
                Thread.sleep(30000);
            }catch (JSONException e){
                e.printStackTrace();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public JSONArray getSfcNodeData() {
        return sfcNodeData;
    }
}
