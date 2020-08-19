package mybatis.learn.dao;

import java.util.List;

import mybatis.learn.pojo.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface OrderDao {

    @Select("SELECT * FROM order_mybatis")
    @Results({ @Result(property = "productName", column = "product_Name") }) // 类属性名和数据库字段名映射
    List<Order> findAll();

    @Insert("INSERT INTO order_mybatis(id, orderno, product_Name) values(#{id}, #{orderno}, #{productName})")
    void insert(Order order);

    @Delete("DELETE  FROM order_mybatis WHERE id = #{id}")
    void delete(@Param("id") int id);

    @Update("UPDATE order_mybatis SET orderno = #{orderno}, product_Name = #{productName} WHERE id = #{id}")
    void update(Order order);

    @Select("SELECT * FROM order_mybatis WHERE id = #{id}")
    @Results({ @Result(property = "productName", column = "product_Name") }) // 类属性名和数据库字段名映射
    Order findById(int id);
}
