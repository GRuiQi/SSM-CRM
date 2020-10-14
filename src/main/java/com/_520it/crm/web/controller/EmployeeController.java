package com._520it.crm.web.controller;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Menu;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQueryObject;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.service.IMenuService;
import com._520it.crm.service.IPermissionService;
import com._520it.crm.util.AjaxResult;
import com._520it.crm.util.PermissionUtil;
import com._520it.crm.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IMenuService menuService;

    @RequestMapping("/employee")
    public String index(){
        return "employee";
    }




    @ResponseBody
    @RequestMapping("/employee_list")
    public PageResult list(EmployeeQueryObject queryObject){

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
    public AjaxResult login(String username, String password, HttpServletRequest request){

        /* AOP日志相关：把request放入当前线程 */
        UserContext.set(request);

        AjaxResult result = null;

        Employee user = employeeService.queryByLogin(username,password);

        if(user!=null){
            result = new AjaxResult("登陆成功",true);
            //把用户信息放入session
            request.getSession().setAttribute(UserContext.USER_IN_SESSION,user);
            //把用户拥有的权限放入session
            List<String> userPermissions = permissionService.queryResourceByEid(user.getId());
            System.out.println("currentUser 's permissionService:"+userPermissions);
            request.getSession().setAttribute(UserContext.PERMISSION_IN_SESSION,userPermissions);

            /**
             * 把用户能访问到的菜单存入session
             * 1.先查出系统所有菜单
             * 2.根据当前用户拥有的权限，筛选出用户专属菜单
             */
            List<Menu> menuList = menuService.queryForMenu();
            //从全部菜单中剔除用户无权访问的菜单
            PermissionUtil.checkMenuPermission(menuList);
            request.getSession().setAttribute(UserContext.MENU_IN_SESSION,menuList);


        }else{
            result = new AjaxResult("账号密码有误");
        }

        return result;
    }

    @RequestMapping("/role_queryByEid")
    @ResponseBody
    public List queryByEid(Long eid){
        return employeeService.queryRoleByEid(eid);
    }

}
