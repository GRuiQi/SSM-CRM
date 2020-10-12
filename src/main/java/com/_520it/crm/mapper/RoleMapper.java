package com._520it.crm.mapper;

import com._520it.crm.domain.Role;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.RoleQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    Long queryForPageCount(RoleQueryObject queryObject);

    List<Role> queryForPage(RoleQueryObject queryObject);

    void insertRelation(@Param("rid")Long id, @Param("pid")Long id1);

    void deletePermissionByRid(Long id);
}