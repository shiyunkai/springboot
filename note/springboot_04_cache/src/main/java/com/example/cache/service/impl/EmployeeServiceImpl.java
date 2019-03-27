package com.example.cache.service.impl;

import com.example.cache.domain.Employee;
import com.example.cache.mapper.EmployeeMapper;
import com.example.cache.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/26 11:20
 * @Description:
 */
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Cacheable(value = "emp",key = "root.caches.get")
    @Override
    public Employee findEmpById(Integer id) {
        Employee emp = employeeMapper.getEmpById(id);
        log.warn("{}号员工被查询",id);
        return emp;
    }

    @Override
    public int addEmp(Employee emp) {
        return employeeMapper.addEmp(emp);
    }

}
