package com.example.cache.service;

import com.example.cache.domain.Employee;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/26 11:19
 * @Description:
 */
public interface EmployeeService {

    Employee findEmpById(Integer id);

    int addEmp(Employee emp);
}
