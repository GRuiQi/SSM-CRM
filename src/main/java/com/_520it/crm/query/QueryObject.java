package com._520it.crm.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QueryObject {
    private Integer page;  //总页数
    private Integer rows;  //每页个数
    public Integer getStart(){  //起始位置
        return (page-1)*rows;
    }
}
