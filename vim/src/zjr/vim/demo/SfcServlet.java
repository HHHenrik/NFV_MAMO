package zjr.vim.demo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import zjr.vim.staticData.ThreadData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "SfcServlet", urlPatterns = {"/sfcData"})
public class SfcServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        JSONArray sfcData = ThreadData.sfcThread.getSfcData();
        ThreadData.sfcThread.sfcFlag = true;
        System.out.println(sfcData);
        String sfcJson = sfcData.toString();
        OutputStream out = response.getOutputStream();
        out.write(sfcJson.getBytes("UTF-8"));
        out.flush();
    }
}
