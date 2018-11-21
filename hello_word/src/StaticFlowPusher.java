import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.util.HashMap;

public class StaticFlowPusher {
    private String basepath;
    public StaticFlowPusher(String ip, int port) {
        this.basepath = "http://" + ip + ":" + Integer.toString(port);
    }

    private int rest_call(String action, String path, String data) {
        int ret = 0;
        try {
            String url = this.basepath + path;
            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse hrps = null;
            if(action == "POST")
            {
                HttpPost post = new HttpPost(url);
                StringEntity entity = new StringEntity(data,"utf-8");
                post.setEntity(entity);
                hrps = client.execute(post);
            }
            else if (action == "DELETE")
            {
                HttpDeleteWithBody delete = new HttpDeleteWithBody(url);
                StringEntity entity = new StringEntity(data,"utf-8");
                delete.setEntity(entity);
                hrps = client.execute(delete);
            }
            else
            {
                HttpGet get = new HttpGet(url);
                hrps = client.execute(get);
            }
            ret = hrps.getStatusLine().getStatusCode();
        } catch (Exception e) {

        }
        return ret;
    }

    public int set_entry(HashMap entry) {
        JSONObject jentry = new JSONObject(entry);
        String path = "/wm/staticflowpusher/json";
        return this.rest_call("POST", path, jentry.toString());
    }

    public  int delete_entry(HashMap entry){
        String path = "/wm/staticflowpusher/json";
        String data = "{\"name\":\"" + entry.get("name").toString() + "\"}";
        return this.rest_call("DELETE",path,data);
    }

    public int clear_entry() {
        String path = "/wm/staticflowpusher/clear/all/json";
        return this.rest_call("GET", path, null);
    }
}