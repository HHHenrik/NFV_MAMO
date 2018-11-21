package zjr.assm.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zjr.assm.demo.po.User;
import zjr.assm.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/html")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users/userList.json")
    public @ResponseBody List<User> getUserList(){
        List<User> res = userService.getAllUsers();
        System.out.println(res.get(0).toString());
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/users/addUser", method = RequestMethod.POST)
    public @ResponseBody void addNewUser(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username); user.setPassword(password);
        userService.addUser(user);
    }

    @RequestMapping(value = "deleteUser/{userName}", method = RequestMethod.POST)
    public @ResponseBody void removeUser(@PathVariable("userName") String userName){
        userService.deleteUser(userName);
    }
}
