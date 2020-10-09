package com._520it.crm.service.impl;

import com._520it.crm.domain.Employee;
import com._520it.crm.mapper.EmployeeMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    /**
     * 分页查询
     * @param queryObject
     * @return
     */
    @Override
    public PageResult queryForPage(QueryObject queryObject) {
        //查询总记录数
        Long count = employeeDao.queryForPageCount(queryObject);
        if(count==0){
            return new PageResult(0,Collections.emptyList());
        }
        List<Employee> result = employeeDao.queryForPage(queryObject);

        return new PageResult(count.intValue(),result);
    }

    @Override
    public void updateState(Long id) {
        employeeDao.updateState(id);
    }
}
