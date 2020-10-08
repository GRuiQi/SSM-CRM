package com._520it.crm.web.controller;

import com._520it.crm.domain.Employee;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/employee")
    public String index(){
        return "employee";
    }

    @ResponseBody
    @RequestMapping("/employee_list")
    public PageResult list(QueryObject queryObject){
        PageResult pageResult = null;
        pageResult = employeeService.queryForPage(queryObject);
        return pageResult;
    }


    @ResponseBody
    @RequestMapping("/login")
    public Map login(String username, String password,HttpSession session){
        Map<String,Object> result = new HashMap<>();
        Employee user = employeeService.queryByLogin(username,password);
        if(user!=null){
            session.setAttribute(UserContext.USER_IN_SESSION,user);
            result.put("success",true);
            result.put("msg","登陆成功");
        }else{
            result.put("success",false);
            result.put("msg","账号密码有误");
        }

        return result;
    }
}
