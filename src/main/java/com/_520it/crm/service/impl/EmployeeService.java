package com._520it.crm.service.impl;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Role;
import com._520it.crm.mapper.EmployeeMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        //先根据id删除中间表emp_role的信息
        employeeMapper.deleteRelation(id);
        //删除employee信息，本项目中没有调用这个方法
        return employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Employee record) {
       //先把员工id插入employee
        int effectedCount = employeeMapper.insert(record);
        //把员工拥有的角色插入emp_role，以此来关联员工和角色
        for(Role role:record.getRoles()){
            employeeMapper.insertRelation(record.getId(),role.getId());
        }
        return effectedCount;
    }

    @Override
    public Employee selectByPrimaryKey(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Employee record) {
        //更新employee表
        int effectedCount = employeeMapper.updateByPrimaryKey(record);
        //根据eid删除emp_role中该员工原有的角色(旧)
        employeeMapper.deleteRelation(record.getId());
        //把员工拥有的角色(新)插入emp_role，以此来关联员工和角色
        for(Role role:record.getRoles()){
            employeeMapper.insertRelation(record.getId(),role.getId());
        }
        return effectedCount;
    }

    @Override
    public Employee queryByLogin(String username,String password) {
        return employeeMapper.queryByLogin(username,password);
    }

    /**
     * 分页查询
     * @param queryObject
     * @return
     */
    @Override
    public PageResult queryForPage(QueryObject queryObject) {
        //查询总记录数
        Long count = employeeMapper.queryForPageCount(queryObject);
        if(count==0){
            return new PageResult(0,Collections.emptyList());
        }
        List<Employee> result = employeeMapper.queryForPage(queryObject);

        return new PageResult(count.intValue(),result);
    }

    @Override
    public void updateState(Long id) {
        employeeMapper.updateState(id);
    }

    @Override
    public List<Long> queryRoleByEid(Long eid) {
        return employeeMapper.queryRoleByEid(eid);
    }
}
