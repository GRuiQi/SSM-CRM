package com._520it.crm.domain;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Department {

    private Long id;  //ID

    private String sn;  //部门编号

    private String name;  //部门名称

    private Employee mananger;  //部门经理

    private Department parent;  //上级部门

    private Boolean state;  //状态


}