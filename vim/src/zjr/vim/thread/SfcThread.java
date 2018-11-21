package zjr.vim.thread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SfcThread implements Runnable {
    private int sfcId = 1;
    private int alarmLevel = 0;
    private int packageReceive = 70;
    private int packageDeal = 70;
    private int packageLoss = 0;
    private float throughput = 100;

    private JSONArray sfcData = new JSONArray();
    public volatile boolean sfcFlag = false;
    private ReentrantReadWriteLock sfcLock = new ReentrantReadWriteLock();

    @Override
    public void run() {
        while (true){
            sfcLock.writeLock().lock();
            try{
                if (sfcFlag){
                    sfcData = new JSONArray();
                    sfcFlag = false;
                }
                int flag = Math.random() > 0.5 ? 1 : 0;
                int offset = 0;
                if (flag == 1){
                    Random random = new Random();
                    offset = random.nextInt(30) + 30;
                    packageReceive = 70 + offset;
                    offset = random.nextInt(30);
                    packageDeal = packageReceive - offset;
                    packageLoss = offset;
                    throughput = packageDeal / packageReceive;
                    DecimalFormat df = new DecimalFormat("#.00");
                    df.format(throughput);
                }else if (flag == 0){
                    Random random = new Random();
                    offset = random.nextInt(30) + 30;
                    packageReceive = 70 - offset;
                    offset = random.nextInt(30);
                    packageDeal = packageReceive - offset;
                    packageLoss = offset;
                    throughput = packageDeal / packageReceive;
                    DecimalFormat df = new DecimalFormat("#.00");
                    df.format(throughput);
                }
                if (offset == 0){
                    alarmLevel = 0;
                }
                else if (offset > 0 && offset < 10){
                    alarmLevel = 1;
                }else if (offset >= 10 && offset < 20){
                    alarmLevel = 2;
                }else if (offset >= 20 && offset < 30){
                    alarmLevel = 3;
                }else if (offset == 30){
                    alarmLevel = 4;
                }
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(date);

                JSONObject sfc = new JSONObject();
                sfc.put("sfcId", sfcId);
                sfc.put("package_receive", packageReceive);
                sfc.put("package_loss", packageLoss);
                sfc.put("package_deal", packageDeal);
                sfc.put("throughput", throughput);
                sfc.put("alarm_level", alarmLevel);
                sfc.put("current_time", currentTime);
                sfcData.put(sfc);

                sfcLock.writeLock().unlock();
                Thread.sleep(30000);
            }catch (JSONException e){
                e.printStackTrace();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public JSONArray getSfcData() {
        return sfcData;
    }
}
