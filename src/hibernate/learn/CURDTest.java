package hibernate.learn;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import hibernate.learn.annotation_JPA.pojo.Customer;
import hibernate.learn.annotation_JPA.pojo.Order;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;

/**
 * CURD + HQL/SQL/Criteria
 */
public class CURDTest {

    private static Configuration cfg = new Configuration().configure("hibernate/learn/hibernate.cfg.xml");
    private static SessionFactory factory = cfg.buildSessionFactory();

    /**
     * 让外部获取Session对象
     */
    public static Session openSession() {
        return factory.openSession();
    }

    /**
     * save(Object obj) : 保存对象
     */
    @Test
    public void test1() {
        Customer customer = new Customer();
        customer.setName("老王3");
        customer.setAge(40);
        customer.setGender("男");
//        customer.setLevel("VIP客户");

        Session session = openSession();

        //开启事务
        Transaction tx = session.beginTransaction();

        //执行添加操作
        session.save(customer);

        //提交事务
        tx.commit();

        //关闭资源
        session.close();
    }

    /**
     * update(Object obj)： 更新对象
     */
    @Test
    public void test2() {
        Customer customer = new Customer();
        //给Customer的id赋值，才可以更新
        customer.setId(6);
        customer.setName("老王44444");
        customer.setAge(45);
        customer.setGender("男");
//        customer.setLevel("VIP客户");

        Session session = openSession();

        //开启事务
        Transaction tx = session.beginTransaction();

        //执行添加操作
        session.update(customer);

        //提交事务
        tx.commit();

        //关闭资源
        session.close();
    }

    /**
     * saveOrUpdate(Object obj): 添加或修改对象
     */
    @Test
    public void test3() {
        Customer customer = new Customer();
        //给Customer的id赋值，才可以更新
        customer.setId(6);
        customer.setName("老王666");
        customer.setAge(45);
        customer.setGender("男");
//        customer.setLevel("VIP客户");

        Session session = openSession();

        //开启事务
        Transaction tx = session.beginTransaction();

        //执行添加操作
        session.saveOrUpdate(customer);

        //提交事务
        tx.commit();

        //关闭资源
        session.close();
    }

    /**
     * delete(Object obj): 删除对象
     */
    @Test
    public void test4() {

        Session session = openSession();

        //开启事务
        Transaction tx = session.beginTransaction();

        //执行添加操作
        Customer customer = new Customer();
        customer.setId(7);
        session.delete(customer);

        //提交事务
        tx.commit();

        //关闭资源
        session.close();
    }

    /**
     * get(Class clz,Serialize id): 获取对象
     * load(Class clz,Serialize id): 获取对象，
     * load延迟加载：当前得到的这个对象其实是一个代理对象，这个代理对象只保存了实体对象的id值，
     * 当使用这个对象得到其它属性时，才会发出sql语句，从数据库中去查询对象。
     */
    @Test
    public void test5() {

        Session session = openSession();

        //开启事务
        Transaction tx = session.beginTransaction();

        //执行添加操作
        //Customer cust = session.get(Customer.class, 6);
        Customer cust = session.load(Customer.class, 6);
        System.out.println(cust);

        //提交事务
        tx.commit();

        //关闭资源
        session.close();
    }


    /**
     * 全表查询
     */
    @Test
    public void testAllHQL() {
        Session session = openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from Order");
        List<Order> list = query.list();
        for (Order order : list) {
            System.out.println(order);
        }

        tx.commit();
        session.close();
    }

    @Test
    public void testAllSQL() {
        Session session = openSession();
        Transaction tx = session.beginTransaction();

        // 以对象数组封装
        SQLQuery sqlQuery = session.createSQLQuery("select * from t_order");
        List<Object[]> list = sqlQuery.list();
        for (Object[] objects : list) {
            for (Object object : objects) {
                System.out.print(object + "\t");
            }
            System.out.println();
        }

        // 以JavaBean对象封装
        sqlQuery.addEntity(Order.class);
        List<Order> list1 = sqlQuery.list();
        for (Order order : list1) {
            System.out.println(order);
        }

        tx.commit();
        session.close();
    }

    @Test
    public void testAllCriteria() {
        Session session = openSession();
        Transaction tx = session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        criteriaQuery.from(Customer.class);

        Query<Customer> query = session.createQuery(criteriaQuery);
        for (Customer customer : query.list()) {
            System.out.println(customer);
        }

        tx.commit();
        session.close();
    }


    /**
     * 条件查询
     */
    @Test
    public void testConditionHQL() {
        Session session = openSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from Order where orderno = '201709070001'");
        List<Order> list = query.list();
        for (Order order : list) {
            System.out.println(order);
        }

        tx.commit();
        session.close();
    }

    @Test
    public void testConditionCriteria() {
        Session session = openSession();
        Transaction tx = session.beginTransaction();

        /** select * from t_customer where c_id = 2; */
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        // select *
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        // from t_customer
        Root<Customer> root = criteriaQuery.from(Customer.class);
        // where c_id = 2
        Predicate condition = criteriaBuilder.equal(root.get("id"), 2);
        CriteriaQuery<Customer> where = criteriaQuery.where(condition);

        Query<Customer> query = session.createQuery(criteriaQuery);
        for (Customer customer : query.list()) {
            System.out.println(customer);
        }

        tx.commit();
        session.close();
    }


}
