package com._520it.crm.util;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Permission;
import com._520it.crm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PermissionUtil {

    /**
     * spring能给对象属性注入值
     * 什么是对象属性？非静态字段
     * 静态字段属于类，不属于对象！
     *
     */

    private static IPermissionService permissionService;

    @Autowired
    public void setPermissionService(IPermissionService permissionService){
        PermissionUtil.permissionService = permissionService;
    }

    public static boolean checkPermission(String function){
        /**
         * 如果是超级管理员直接放行
         *
         * 拿到当前系统所有权限资源（需要验证的url）判断当前方法是否包含其中
         *  1. 不包含：不需要权限判断，返回true
         *  2. 包含，进一步判断当前用户是否拥有该权限
         *      1)拥有：返回true,放行（ALL权限放行）
         *      2)没有：返回false,拦截
         */

        //如果是超级管理员，直接放行
        Employee current = (Employee)UserContext.get().getSession().getAttribute(UserContext.USER_IN_SESSION);
        if(current.getAdmin()){
            return true;
        }

        //得到当前系统的所有权限（需要权限验证的url的）
        if(CommonUtil.ALL_PERMISSIONS.size()==0){
            //从数据库查询当前系统所有权限资源，封装都ALL_PERMISSIONS
            List<Permission> permissions = permissionService.selectAll();
            for(Permission permission:permissions){
                CommonUtil.ALL_PERMISSIONS.add(permission.getResource());
            }
        }

        //如果当前访问的url不在系统所有权限中，说明不需要验证，否则需要验证
        if(CommonUtil.ALL_PERMISSIONS.contains(function)){
            //当前访问的方法需要权限验证，所以查看当前的用户是否拥有该权限
            List<String> userPermissions = (List<String>)UserContext.get().getSession().getAttribute(UserContext.PERMISSION_IN_SESSION);
            if(userPermissions.contains(function)){
                return true;
            }else{
                //ALL权限匹配
                String allPermission = function.split(":")[0]+":ALL";
                if(userPermissions.contains(allPermission)){
                    return true;
                }else{
                    return false;
                }
            }
        }else{
            //当前访问的方法不需要权限验证，直接放行
            return true;
        }
    }
}
