package zjr.assm.demo.dao;

import org.springframework.stereotype.Repository;
import zjr.assm.demo.po.User;

import java.util.List;

//��@Repository����һ��DAO BEAN
@Repository
public interface UserDao {
    List<User> getAllUsers();
    void addUser(User user);
    void deleteUser(String username);
    int getMatchUserCount(User user);
    User findUserByName(String username);
}
