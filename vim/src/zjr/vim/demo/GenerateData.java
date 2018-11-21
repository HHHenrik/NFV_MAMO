package zjr.vim.demo;

import zjr.vim.staticData.ThreadData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GenerateData",  urlPatterns = {"/generateData"})
public class GenerateData extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Thread phyLinkThread = new Thread(ThreadData.phyLinkThread);
        Thread phyNodeThread = new Thread(ThreadData.phyNodeThread);

        Thread sfcThread = new Thread(ThreadData.sfcThread);
        Thread sfcLinkThread = new Thread(ThreadData.sfcLinkThread);
        Thread sfcNodeThread = new Thread(ThreadData.sfcNodeThread);

        phyLinkThread.start();
        phyNodeThread.start();

        sfcThread.start();
        sfcLinkThread.start();
        sfcNodeThread.start();
    }
}
