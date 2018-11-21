package zjr.vim.thread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PhyNodeThread implements Runnable{
    private String serverName = "server1";
    private int alarmLevel = 0;
    private float cpuUtilRate = 60;
    private float memoryUtilRate = 70;
    private float storageUtilRate = 80;
    private float cpuThreUp = 90;
    private float cpuThreDown = 10;
    private float memoryThreUp = 85;
    private float memoryThreDown = 15;
    private float storageThreUp = 90;
    private float storageThreDown = 5;
    private JSONArray phyNodeData = new JSONArray();
    public volatile boolean phyNodeFlag = false;
    private ReentrantReadWriteLock phyNodeLock = new ReentrantReadWriteLock();

    @Override
    public void run(){
        while (true){
            phyNodeLock.writeLock().lock();
            try{
                if (phyNodeFlag){
                    phyNodeData = new JSONArray();
                    phyNodeFlag = false;
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
                }else if (flag == 0){
                    Random random = new Random();
                    int offset = random.nextInt(13);
                    cpuUtilRate = 60 - offset * 5;
                    offset = random.nextInt(15);
                    memoryUtilRate = 70 - offset * 5;
                    offset = random.nextInt(17);
                    storageUtilRate = 80 - offset * 5;
                }
                if (cpuUtilRate >= 90 && cpuUtilRate < 95 || cpuUtilRate <= 10 && cpuUtilRate > 5)
                    alarmLevel = 1;
                else if (cpuUtilRate >= 95 && cpuUtilRate < 100 || cpuUtilRate <= 5 && cpuUtilRate > 0)
                    alarmLevel = 2;
                else if (alarmLevel == 100 || alarmLevel == 0)
                    alarmLevel = 3;
                else
                    alarmLevel = 0;
                phyNode.put("nodeId", serverName);
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(date);
                phyNode.put("current_time", currentTime);
                phyNode.put("alarm_level", alarmLevel);
                phyNode.put("cpu_util_rate", cpuUtilRate);
                phyNode.put("memory_util_rate", memoryUtilRate);
                phyNode.put("storage_util_rate", storageUtilRate);
                phyNode.put("cpu_thre_up", cpuThreUp);
                phyNode.put("cpu_thre_down", cpuThreDown);
                phyNode.put("memory_thre_up", memoryThreUp);
                phyNode.put("memory_thre_down", memoryThreDown);
                phyNode.put("storage_thre_up", storageThreUp);
                phyNode.put("storage_thre_down", storageThreDown);
                phyNodeData.put(phyNode);
                System.out.println(phyNodeData);
                phyNodeLock.writeLock().unlock();
                Thread.sleep(30000);
            }catch (JSONException e){
                e.printStackTrace();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public JSONArray getPhyNodeData() {
        return phyNodeData;
    }
}
