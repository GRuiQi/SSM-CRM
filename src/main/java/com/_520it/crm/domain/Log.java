package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Log {
    private Long id;

    private Employee opuser;

    private Date optime;

    private String opip;

    private String function;

    private String params;

}