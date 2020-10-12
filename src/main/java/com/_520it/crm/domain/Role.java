package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Role {
    private Long id;

    private String name;

    private String sn;

    private List<Permission> permissions = new ArrayList<>();

}