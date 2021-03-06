package zjr.vim.demo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@WebServlet(name = "SfcDeployController", urlPatterns = {"/sfcDeployController"})
public class SfcDeployController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        InputStream in = request.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        String s = bufferedReader.readLine();
        try{
            JSONObject jsonObject = new JSONObject(s);
//            System.out.println(jsonObject);
            int sfcId = jsonObject.getInt("sfcid");
            JSONArray jsonArray = jsonObject.getJSONArray("nodes");
            System.out.println(sfcId);
            System.out.println(jsonArray);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
