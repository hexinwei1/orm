package mybatis.learn;

import java.io.InputStream;

import mybatis.learn.dao.CustomerDao;
import mybatis.learn.pojo.Customer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class CURDTest {

    public static SqlSessionFactory factory;

    static {
        try {
            //1.加载SqlMapConfig.xml
            InputStream in = Resources.getResourceAsStream("mybatis/learn/SqlMapConfig.xml");
            //2.创建SqlSessionFactory工厂
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            factory = builder.build(in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSession openSession() {
        return factory.openSession(true);
    }

    @Test
    public void save() {
        Customer customer = new Customer();
        customer.setName("小明");
        customer.setGender('男');
        customer.setTelephone("13000000");

        SqlSession sqlSession = openSession();
        CustomerDao userDao = sqlSession.getMapper(CustomerDao.class);
        userDao.save(customer);
        sqlSession.close();
    }

    @Test
    public void delete() {
        SqlSession sqlSession = openSession();
        CustomerDao userDao = sqlSession.getMapper(CustomerDao.class);
        userDao.delete(2);
        sqlSession.close();
    }

    @Test
    public void update() {
        Customer customer = new Customer();
        customer.setId(9);
        customer.setName("小红");
        customer.setGender('女');
        customer.setTelephone("122345964");

        SqlSession sqlSession = openSession();
        CustomerDao userDao = sqlSession.getMapper(CustomerDao.class);
        userDao.update(customer);
        sqlSession.close();
    }

    @Test
    public void findById() {
        SqlSession sqlSession = openSession();
        CustomerDao userDao = sqlSession.getMapper(CustomerDao.class);
        Customer customer = userDao.findById(9);
        System.out.println(customer);
        sqlSession.close();
    }



}