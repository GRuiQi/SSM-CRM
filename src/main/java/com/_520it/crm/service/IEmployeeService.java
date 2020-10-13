package com._520it.crm.service;

import com._520it.crm.domain.Employee;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface IEmployeeService {

    /**
     * 删除也需要维护中间表
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 需要维护中间表emp_role
     * @param record
     * @return
     */
    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    /**
     * 删除旧的角色，维护中间表
     * @param record
     * @return
     */
    int updateByPrimaryKey(Employee record);

    Employee queryByLogin(String username,String password);

    PageResult queryForPage(QueryObject queryObject);

    void updateState(Long id);

    List<Long> queryRoleByEid(Long eid);
}
