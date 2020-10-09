package com._520it.crm.web.controller;

import com._520it.crm.domain.Employee;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.util.AjaxResult;
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
        //System.out.println("ueryObject"+queryObject);
        PageResult pageResult = null;
        System.out.println("queryObject.getKeyword()"+queryObject.getKeyword());
        pageResult = employeeService.queryForPage(queryObject);
        return pageResult;
    }

    @ResponseBody
    @RequestMapping("/employee_delete")
    public AjaxResult delete(Long id){
        AjaxResult result = null;
        try{

           employeeService.updateState(id);
           result = new AjaxResult("删除成功",true);
        }catch (Exception e){
           result = new AjaxResult("删除失败，请联系管理员");

        }
        return result;
    }



    @ResponseBody
    @RequestMapping("/employee_update")
    public AjaxResult update(Employee emp){
        AjaxResult result = null;
        try{

            employeeService.updateByPrimaryKey(emp);
            result = new AjaxResult("更新成功",true);
        }catch (Exception e){
            result = new AjaxResult("更新异常，请联系管理员");

        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/employee_save")
    public AjaxResult save(Employee emp){
        AjaxResult result = null;
        try{
            emp.setPassword("888");
            emp.setAdmin(false);
            emp.setState(true);
            employeeService.insert(emp);
            result = new AjaxResult("保存成功",true);
        }catch (Exception e){

            result = new AjaxResult("保存异常，请联系管理员");

        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/login")
    public AjaxResult login(String username, String password,HttpSession session){
        AjaxResult result = null;
        Employee user = employeeService.queryByLogin(username,password);
        if(user!=null){
            session.setAttribute(UserContext.USER_IN_SESSION,user);
            result = new AjaxResult("登陆成功",true);
        }else{
            result = new AjaxResult("账号密码有误");
        }

        return result;
    }


}
