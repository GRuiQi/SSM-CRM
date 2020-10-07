package com._520it.crm.service;

import com._520it.crm.domain.Employee;

import java.util.List;
import java.util.Map;

public interface IEmployeeService {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    Employee queryByLogin(String username,String password);
}
