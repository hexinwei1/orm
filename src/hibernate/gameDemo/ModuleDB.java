package hibernate.gameDemo;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import hibernate.learn.annotation_JPA.pojo.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ModuleDB {

    private static Configuration cfg = new Configuration().configure("hibernate/gameDemo/hibernate.cfg.xml");
    private static SessionFactory factory = cfg.buildSessionFactory();

    /**
     * 让外部获取Session对象
     */
    public static Session openSession() {
        return factory.openSession();
    }

    /**
     * 增
     */
    public static void insert(Object object) {
        Session session = openSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
    }

    /**
     * 删
     */
    public static void delete(Object object) {
        Session session = openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(object) ;
        transaction.commit();
        session.close();
    }

    /**
     * 改
     */
    public static void update(Object object) {
        Session session = openSession();
        Transaction transaction = session.beginTransaction();
        session.update(object) ;
        transaction.commit();
        session.close();
    }

    /**
     * 查
     */
    public static Object findById(Class clazz, long id) {
        Session session = openSession();
        Object result = session.get(clazz, id);
        session.close();
        return result;
    }

    public static List<? extends Object> findByHumanId(Class clazz, long humanId) {
        return findByField(clazz, "humanId", humanId);
    }

    public static List<? extends Object> findByField(Class clazz, String fieldStr, Object fieldValue) {
        Session session = openSession();

        /** select * from item where humanId = 1001; */
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        // select *
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(clazz);
        // from t_customer
        Root<Customer> root = criteriaQuery.from(clazz);
        // where c_id = 2
        Predicate condition = criteriaBuilder.equal(root.get(fieldStr), fieldValue);
        criteriaQuery.where(condition);
        // 查询
        Query<Customer> query = session.createQuery(criteriaQuery);
        List<? extends Object> list = query.list();

        session.close();
        return list;
    }



}
