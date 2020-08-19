package hibernate.learn;

import hibernate.learn.pojo.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * HelloWorld
 */
public class HelloWorldTest {

    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.setName("老王");
        customer.setAge(40);
        customer.setGender("男");
        customer.setLevel("VIP客户");

        //1.读取hibernate.cfg.xml文件：Configuration类负责管理Hibernate的配置信息
        Configuration cfg = new Configuration().configure("hibernate/learn/hibernate.cfg.xml");

        //2.创建SessionFactory工厂：一个SessionFactory对应一个数据库，包括数据库配置、映射关系、二级缓存...
        SessionFactory factory = cfg.buildSessionFactory();

        //3.创建Session对象：一级缓存，不是线程安全的
        Session session = factory.openSession();

        //4.开启事务
        Transaction tx = session.beginTransaction();

        //5.执行添加操作
        session.save(customer);

        //6.提交事务
        tx.commit();

        //7.关闭资源
        session.close();
    }
}
