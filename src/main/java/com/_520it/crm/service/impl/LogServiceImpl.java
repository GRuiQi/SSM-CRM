package com._520it.crm.service.impl;


import com._520it.crm.domain.Log;
import com._520it.crm.mapper.LogMapper;
import com._520it.crm.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return logMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Log record) {
        return logMapper.insert(record);
    }

    @Override
    public Log selectByPrimaryKey(Long id) {
        return logMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Log> selectAll() {
        return logMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Log record) {
        return logMapper.updateByPrimaryKey(record);
    }
}
