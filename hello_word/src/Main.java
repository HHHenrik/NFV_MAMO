import java.io.*;

import org.apache.commons.io.IOUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Server running!");
        getTopo();
        try{
            HttpServer server = HttpServer.create(new InetSocketAddress(8081), 100);
            server.createContext("/deploy",new DeployHandler());
            server.createContext("/hangup",new HangupHandler());
            server.createContext("/startup",new StartupHandler());
            server.createContext("/delete",new DeleteHandle());
            server.createContext("/clear",new ClearHandle());
            server.createContext("/topo",new TopoHandle());
            server.setExecutor(null);
            server.start();
        }
        catch (Exception e)
        {
        }
    }

    public static JSONObject getTopo()
    {
        File f = new File("d:/demo3.py");
        try {
            HashMap<String,Object> topos = new HashMap<String,Object>();
            List<HashMap<String,Object>> links = new ArrayList<HashMap<String, Object>>();
            List<HashMap<String,Object>> servers = new ArrayList<>();
            List<HashMap<String,Object>> forwardingNodes = new ArrayList<>();
            topos.put("topo",links);
            topos.put("functionalityNode",servers);
            topos.put("forwardingNode",forwardingNodes);

            InputStream is = new FileInputStream(f);
            Scanner scan = new Scanner(is);

            String pattern = "^\\s+self.addLink\\(switches\\[(\\d+)],switches\\[(\\d+)],\\d+,\\d+,bw=(\\d+),delay='(\\d+)ms'\\)";
            int max_switch = 0;
            Pattern r = Pattern.compile(pattern);
            HashMap<String,Object> link;
            HashMap<String,Object> server;
            HashMap<String,Object> forwardingNode;
            while(scan.hasNextLine())
            {
                String nextLine = scan.nextLine();
                Matcher m = r.matcher(nextLine);
                if(!m.find())
                    continue;
                String node1 = m.group(1);
                String node2 = m.group(2);
                link = new HashMap<String,Object>();
                link.put("from","switch" + node1);
                link.put("to","switch" + node2);
                link.put("fromDpId","switch" + node1 + "-dp" + node2);
                link.put("toDpId","switch" + node2 + "-dp" + node1);
                link.put("bandwidth",Integer.parseInt(m.group(3)));
                link.put("delay",Integer.parseInt(m.group(4)));
                links.add(link);

                if(max_switch < Integer.parseInt(node1))
                {
                    max_switch = Integer.parseInt(node1);
                }
                if(max_switch < Integer.parseInt(node2))
                {
                    max_switch = Integer.parseInt(node2);
                }
            }
            for(int i=1;i<=max_switch;i++)
            {
                link = new HashMap<>();
                link.put("from","switch" + Integer.toString(i));
                link.put("to","server" + Integer.toString(i));
                link.put("fromDpId","switch" + Integer.toString(i) + "-dp1");
                link.put("toDpId","server" + Integer.toString(i) + "-dp1");
                link.put("bandwidth",20);
                link.put("delay",10);
                links.add(link);

                server = new HashMap<>();
                server.put("nodeId","server" + Integer.toString(i));
                server.put("total_cpu",12);
                server.put("total_memory",32);
                server.put("total_storage",123);
                server.put("manufacturer","bupt");
                server.put("ava_cpu",5);
                server.put("ava_memory",10);
                server.put("ava_storage",100);
                servers.add(server);

                forwardingNode = new HashMap<>();
                forwardingNode.put("id","switch" + Integer.toString(i));
                forwardingNode.put("capacity",10);
                forwardingNodes.add(forwardingNode);
            }
            return new JSONObject(topos);
        }
        catch(FileNotFoundException e)
        {
        }
        return null;
    }
}

class DeployHandler implements HttpHandler{
    public void handle(HttpExchange t) throws IOException{
        InputStream is = t.getRequestBody();
        String result = IOUtils.toString(is,"UTF-8");
        int ret = 200;
        try {
            JSONObject json = new JSONObject(result);
            int sfcid = (int)json.get("sfcid");
            JSONArray nodes = (JSONArray) json.get("nodes");
            List<Integer> Nodes = new ArrayList<>();
            int len = nodes.length();
            for(int i = 0;i<len;i++)
            {
                Nodes.add(nodes.getInt(i));
            }
            System.out.println(Nodes);
            ret = ControllerHelper.CreateEntries(Nodes,sfcid);
        }
        catch(Exception e)
        {
        }
        String response = "This is deploy response";
        t.sendResponseHeaders(ret,response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

class HangupHandler implements HttpHandler{
    public void handle(HttpExchange t)throws IOException{
        InputStream is = t.getRequestBody();
        String result = IOUtils.toString(is,"UTF-8");
        int ret = 200;
        try{
            JSONObject json = new JSONObject(result);
            int sfcid = json.getInt("sfcid");
            ret = ControllerHelper.DisableEntry(sfcid);
        }
        catch(Exception e)
        {}
        String response = "This is hangup response";
        t.sendResponseHeaders(ret,response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

class StartupHandler implements HttpHandler{
    public void handle(HttpExchange t) throws IOException{
        InputStream is = t.getRequestBody();
        String result = IOUtils.toString(is,"UTF-8");
        int ret = 200;
        try{
            JSONObject json = new JSONObject(result);
            int sfcid = json.getInt("sfcid");
            ret = ControllerHelper.EnableEntry(sfcid);
        }
        catch(Exception e)
        {}
        String response = "This is startup response";
        t.sendResponseHeaders(ret,response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

class DeleteHandle implements HttpHandler{
    public void handle(HttpExchange t) throws IOException{
        InputStream is = t.getRequestBody();
        String result = IOUtils.toString(is,"UTF-8");
        int ret = 200;
        try{
            JSONObject json = new JSONObject(result);
            int sfcid = json.getInt("sfcid");
            ret = ControllerHelper.DeleteEntries(sfcid);
        }
        catch(Exception e)
        {}
        String response = "This is delete response";
        t.sendResponseHeaders(ret,response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

class ClearHandle implements HttpHandler{
    public void handle(HttpExchange t) throws IOException{
        int ret = 200;
        ret = ControllerHelper.ClearEntries();
        String response = "This is clear response";
        t.sendResponseHeaders(ret,response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

class TopoHandle implements HttpHandler{
    public void handle(HttpExchange t) throws IOException{
        String response = Main.getTopo().toString();
        t.sendResponseHeaders(200,response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

