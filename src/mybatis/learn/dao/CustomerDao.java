package mybatis.learn.dao;

import java.util.List;

import mybatis.learn.pojo.Customer;

public interface CustomerDao {

    List<Customer> findAll();

    void save(Customer customer);
    void delete(Integer id);
    void update(Customer customer);
    Customer findById(Integer id);

}
