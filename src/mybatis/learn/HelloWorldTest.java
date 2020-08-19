package mybatis.learn;

import java.io.InputStream;
import java.util.List;

import mybatis.learn.dao.CustomerDao;
import mybatis.learn.pojo.Customer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class HelloWorldTest {

    public static void main(String[] args) throws Exception {
        //1.加载SqlMapConfig.xml
        InputStream in = Resources.getResourceAsStream("mybatis/learn/SqlMapConfig.xml");

        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);

        //3.创建SqlSession
        SqlSession sqlSession = factory.openSession();

        //4.生成Dao接口代理对象
        CustomerDao userDao = sqlSession.getMapper(CustomerDao.class);

        //5.执行Dao接口方法
        List<Customer> list = userDao.findAll();
        for (Customer c : list) {
            System.out.println(c);
        }

        //6.释放资源
        sqlSession.close();
        in.close();
    }
}
