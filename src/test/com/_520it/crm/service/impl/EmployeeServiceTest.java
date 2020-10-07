package com._520it.crm.service.impl;

import com._520it.crm.domain.Employee;
import com._520it.crm.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class EmployeeServiceTest {

    @Autowired
    private IEmployeeService employeeService;
    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        Employee employee = new Employee();
        employee.setUsername("777");
        int i = 1/0;
        employeeService.insert(employee);
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void selectAll() {

    }

    @Test
    public void updateByPrimaryKey() {
    }
}