package zjr.assm.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zjr.assm.demo.dao.UserDao;
import zjr.assm.demo.po.User;
import zjr.assm.demo.service.UserService;

import java.util.List;

@Transactional
//使用@Service将其标注为服务层的一个Bean
@Service
public class UserServiceImpl implements UserService {
//    使用@Autowired将Spring容器中的Bean注入进来,将UserDao层的Bean注入进来
    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public void deleteUser(String username) {
        userDao.deleteUser(username);
    }

    public boolean hasMatchUser(User user){
        int match = userDao.getMatchUserCount(user);
        return match > 0;
    }

    public User findUserByName(String username){
        return userDao.findUserByName(username);
    }
}
