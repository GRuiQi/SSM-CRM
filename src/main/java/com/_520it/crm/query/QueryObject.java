package com._520it.crm.query;

public class QueryObject {
    private Integer page;  //总页数
    private Integer rows;  //每页个数
    public Integer getStart(){  //起始位置
        return (page-1)*rows;
    }
}
