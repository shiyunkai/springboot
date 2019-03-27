package com.example.cache;

import com.example.cache.conf.MyRedisTemplateConfig;
import com.example.cache.domain.Employee;
import com.example.cache.mapper.EmployeeMapper;
import com.example.cache.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CacheApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeService employeeService;

    /* * @Auther: shiyunkai
     * @Description: 测试注解版的mybatis的使用
     * @Param: []
     * @Date:  10:41 2019/3/26
     * @return: void
     **/
    @Test
    public void testMapper(){
        Employee employee = new Employee();
        employee.setId(1);
        employee.setDId(2);
        employee.setEmail("1886971299");
        employee.setGender(1);
        employee.setLastName("kai");
        employeeMapper.deleteEmpById(1);
        //employeeMapper.updateEmp(employee);
        //employeeMapper.addEmp(employee);
        //Employee empById = employeeMapper.getEmpById(1);
        //log.info("--------->"+empById);
    }

    //redis两个template的注入
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<Object, Employee> myTemplate;

    /* * @Auther: shiyunkai
     * @Description: redis template的使用
     * @Param: []
     * @Date:  15:47 2019/3/26
     * @return: void
     **/
    @Test
    public void testRedis(){
        stringRedisTemplate.opsForValue().append("msg","我是dbdbdbdb");
        stringRedisTemplate.opsForValue().append("testmsg","dbdbdbdb");
        Employee emp = employeeService.findEmpById(2);
        //redisTemplate.opsForSet().add("emp1",emp);

        // 测试自己配置的redis template
        myTemplate.opsForSet().add("2号员工",emp);
    }

}
