package com._520it.crm.web.controller;

import com._520it.crm.domain.Department;
import com._520it.crm.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("/department")
    public String index() {
        return "department";
    }

    @RequestMapping("/department_queryForEmployee")
    @ResponseBody
    public List<Department> queryForEmp() {
        // 虽然已经有自动生成的selectAll()，但是我们本次需求其实只要id和name，为了不浪费性能，另写一个（实际工作可能就直接用了，哈哈）
        return departmentService.queryForEmp();
    }

}
