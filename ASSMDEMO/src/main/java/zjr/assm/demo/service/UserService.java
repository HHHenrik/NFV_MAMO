package zjr.assm.demo.service;

import zjr.assm.demo.po.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public void addUser(User user);
    public void deleteUser(String username);
    public boolean hasMatchUser(User user);
    public User findUserByName(String username);
}
