package com._520it.crm.mapper;

import com._520it.crm.domain.Employee;
import com._520it.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    Employee queryByLogin(@Param("username") String username, @Param("password")String password);


    Long queryForPageCount(QueryObject queryObject);

    List<Employee> queryForPage(QueryObject queryObject);


    void updateState(Long id);
}