package com.example.cache.controller;

import com.example.cache.domain.Employee;
import com.example.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/26 10:59
 * @Description:
 */
@RestController
@RequestMapping(value = "employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping(value = "addEmp")
    public int addEmp(@RequestBody Employee employee){
        int flag = employeeService.addEmp(employee);
        return flag;
    }

    @GetMapping(value = "getEmp/{id}")
    public Employee getEmp(@PathVariable Integer id){
        return employeeService.findEmpById(id);
    }

}
