package zjr.assm.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import zjr.assm.demo.po.User;
import zjr.assm.demo.service.UserService;
import zjr.assm.demo.service.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

@Controller
@RequestMapping("/html")
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> loginCheck(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username); user.setPassword(password);
        //判断是否有匹配的用户
        boolean isValidUser = userService.hasMatchUser(user);
        Map<String, Object> map = new HashMap<String, Object>();
        if(!isValidUser){//用户不匹配
            map.put("code","0");
        }else {//用户匹配
            //线程休眠改成Timer,测试是否会复用数据库连接池
            Timer timer = new Timer();
            WritePhyNodeMonitor writePhyNodeMonitor = new WritePhyNodeMonitor();
            WritePhyLinkMonitor writePhyLinkMonitor = new WritePhyLinkMonitor();
            WriteSfcMonitor writeSfcMonitor = new WriteSfcMonitor();
            WriteSfcNodeMonitor writeSfcNodeMonitor = new WriteSfcNodeMonitor();
            WriteSfcLinkMonitor writeSfcLinkMonitor = new WriteSfcLinkMonitor();

            timer.schedule(writePhyLinkMonitor, 0, 300000);
            timer.schedule(writePhyNodeMonitor, 0, 300000);
            timer.schedule(writeSfcLinkMonitor, 0, 300000);
            timer.schedule(writeSfcMonitor, 0, 300000);
            timer.schedule(writeSfcNodeMonitor, 0, 300000);
//            Thread phyNodeThread = new Thread(writePhyNodeMonitor);
//            Thread phyLinkThread = new Thread(writePhyLinkMonitor);
//            Thread sfcThread = new Thread(writeSfcMonitor);
//            Thread sfcNodeThread = new Thread(writeSfcNodeMonitor);
//            Thread sfcLinkThread = new Thread(writeSfcLinkMonitor);
//
//            phyNodeThread.start();
//            phyLinkThread.start();
//            sfcThread.start();
//            sfcNodeThread.start();
//            sfcLinkThread.start();

            map.put("code","1");
        }
        return map;
    }

    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> userRegister(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String, Object> map = new HashMap<String, Object>();
        User user = new User();
        user.setUsername(username);
        boolean isValidUser = userService.hasMatchUser(user);//判断是否存在同名用户
        if (!isValidUser){//用户名合法
            map.put("code","1");
            user.setPassword(password);
            userService.addUser(user);
        }else {//存在同名用户
            map.put("code","2");
        }
        return map;
    }

//    @RequestMapping(value = "/loginTest", method = RequestMethod.GET)
//    public ModelAndView loginTest(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("login");
//        return modelAndView;
//    }
}
