package com._520it.crm.service.impl;

import com._520it.crm.domain.Permission;
import com._520it.crm.domain.Role;
import com._520it.crm.mapper.RoleMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.RoleQueryObject;
import com._520it.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {

        //根据rid删除掉role_permission的信息
        roleMapper.deletePermissionByRid(id);
        //删除role的信息
        int effectedCount = roleMapper.deleteByPrimaryKey(id);
        return effectedCount;

    }

    @Override
    public int insert(Role record) {

        //先把角色插入role表
        int effectedCount = roleMapper.insert(record);
        //把角色拥有的权限插入role_permission，以此来关联角色和权限
        for(Permission permission:record.getPermissions()){
            roleMapper.insertRelation(record.getId(),permission.getId());
        }
        return effectedCount;
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Role record) {

        //更新role表
        int effectedCount = roleMapper.updateByPrimaryKey(record);
        //根据rid删除role_permission中改角色原有的权限(旧)
        roleMapper.deletePermissionByRid(record.getId());
        //把角色拥有的权限(新)插入role_permission，以此关联角色和权限
        for(Permission permission:record.getPermissions()){

            roleMapper.insertRelation(record.getId(),permission.getId());
        }

        return effectedCount;
    }

    @Override
    public PageResult queryForPage(RoleQueryObject queryObject) {

        // 查询总记录数
        Long count = roleMapper.queryForPageCount(queryObject);
        if (count == 0) {
            return new PageResult(0, Collections.emptyList());
        }

        List<Role> result = roleMapper.queryForPage(queryObject);
        return new PageResult(count.intValue(), result);
    }
}
