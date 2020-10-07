package com._520it.crm.service.impl;

import com._520it.crm.domain.Employee;
import com._520it.crm.mapper.EmployeeMapper;
import com._520it.crm.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return employeeDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Employee record) {
        return employeeDao.insert(record);
    }

    @Override
    public Employee selectByPrimaryKey(Long id) {
        return employeeDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> selectAll() {
        return employeeDao.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Employee record) {
        return employeeDao.updateByPrimaryKey(record);
    }

    @Override
    public Employee queryByLogin(String username,String password) {
        return employeeDao.queryByLogin(username,password);
    }
}
