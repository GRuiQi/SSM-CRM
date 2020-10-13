package com._520it.crm.service.impl;

import com._520it.crm.domain.Permission;
import com._520it.crm.mapper.PermissionMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(Permission record) {
        return permissionMapper.insert(record);
    }

    @Override
    public Permission selectByPrimaryKey(Long id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Permission record) {
        return permissionMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryForPage(QueryObject queryObject) {
        // 查询总记录数
        Long count = permissionMapper.queryForPageCount(queryObject);
        if (count == 0) {
            return new PageResult(0, Collections.emptyList());
        }

        List<Permission> result = permissionMapper.queryForPage(queryObject);
        return new PageResult(count.intValue(), result);
    }


    @Override
    public List<String> queryResourceByEid(Long id) {
        return permissionMapper.queryResourceByEid(id);
    }
}
