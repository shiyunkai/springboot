package com.example.cache.mapper;

import com.example.cache.domain.Employee;
import org.apache.ibatis.annotations.*;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/26 10:33
 * @Description:
 */
@Mapper
public interface EmployeeMapper {

    @Select("select * from employee where id = #{id}")
    public Employee getEmpById(Integer id);

    @Update("update employee set last_name=#{lastName}, email=#{email},gender=#{gender},d_id=#{dId} where id=#{id}")
    public int updateEmp(Employee employee);

    @Delete("delete from employee where id=#{id}")
    public int deleteEmpById(Integer id);

    @Insert("insert into employee(last_name,email,gender,d_id) values(#{lastName},#{email},#{gender},#{dId})")
    public int addEmp(Employee employee);
}
