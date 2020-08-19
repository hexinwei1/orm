package mybatis.learn;

import mybatis.learn.dao.OrderDao;
import mybatis.learn.pojo.Order;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class AnnotationTest {


    @Test
    public void findAll() {
        SqlSession sqlSession = CURDTest.openSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        orderDao.findAll().forEach(System.out :: println);
        sqlSession.close();
    }

    @Test
    public void insert() {
        Order order = new Order();
        order.setOrderno("10013");
        order.setProductName("相机");

        SqlSession sqlSession = CURDTest.openSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        orderDao.insert(order);
        sqlSession.close();
    }

    @Test
    public void delete() {
        SqlSession sqlSession = CURDTest.openSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        orderDao.delete(10013);
        sqlSession.close();
    }

    @Test
    public void update() {
        Order order = new Order();
        order.setId(1);
        order.setOrderno("10013");
        order.setProductName("相机");

        SqlSession sqlSession = CURDTest.openSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        orderDao.update(order);
        sqlSession.close();
    }

    @Test
    public void findById() {
        SqlSession sqlSession = CURDTest.openSession();
        OrderDao orderDao = sqlSession.getMapper(OrderDao.class);
        Order order = orderDao.findById(1);
        System.out.println(order);
        sqlSession.close();
    }

}
