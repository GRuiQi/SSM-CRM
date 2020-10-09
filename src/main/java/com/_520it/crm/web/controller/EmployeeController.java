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
        //System.out.println(queryObject);
        PageResult pageResult = null;
        pageResult = employeeService.queryForPage(queryObject);
        return pageResult;
    }

    @ResponseBody
    @RequestMapping("/employee_delete")
    public Map<String,Object> delete(Long id){
        Map<String,Object> result = new HashMap<String,Object>();
        try{

            employeeService.updateState(id);
            result.put("success",true);
            result.put("msg","保存成功");
        }catch (Exception e){
            result.put("success",false);
            result.put("msg","保存异常，请联系管理员");

        }
        return result;
    }



    @ResponseBody
    @RequestMapping("/employee_update")
    public Map<String,Object> update(Employee emp){
        Map<String,Object> result = new HashMap<String,Object>();
        try{

            employeeService.updateByPrimaryKey(emp);
            result.put("success",true);
            result.put("msg","保存成功");
        }catch (Exception e){
            result.put("success",false);
            result.put("msg","保存异常，请联系管理员");

        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/employee_save")
    public Map<String,Object> save(Employee emp){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            emp.setPassword("888");
            emp.setAdmin(false);
            emp.setState(true);
            employeeService.insert(emp);
            result.put("success",true);
            result.put("msg","保存成功");
        }catch (Exception e){
            result.put("success",false);
            result.put("msg","保存异常，请联系管理员");

        }
        return result;
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
