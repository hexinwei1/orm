package hibernate.learn.annotation_JPA;

import hibernate.learn.annotation_JPA.pojo.Customer;
import hibernate.learn.annotation_JPA.pojo.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class annotationTest {

    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.setName("老张");
        customer.setAge(40);
        customer.setGender("男");

        Order order = new Order();
        order.setOrderno("10009");
        order.setProductName("book");

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
        session.save(order);

        //6.提交事务
        tx.commit();

        //7.关闭资源
        session.close();
    }
}
