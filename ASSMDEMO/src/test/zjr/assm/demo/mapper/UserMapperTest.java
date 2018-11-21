package zjr.assm.demo.mapper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zjr.assm.demo.dao.UserDao;

public class UserMapperTest {
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/ApplicationContext.xml");
    }

    @Test
    public void getAllUsers() {
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        System.out.println(userDao.getAllUsers().get(0).toString());
    }

    @Test
    public void addUser() {

    }

    @Test
    public void deleteUser() {
    }
}