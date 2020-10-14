package com._520it.crm.util;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Menu;
import com._520it.crm.domain.Permission;
import com._520it.crm.service.IPermissionService;
import org.apache.commons.lang.StringUtils;
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


    /**
     * 根据当前用户权限，从全部菜单中筛选出用户能够访问的菜单
     * @param menuList
     */
    public static void checkMenuPermission(List<Menu> menuList) {
        //用户拥有的权限
        List<String> userPermissions = (List<String>)UserContext.get().getSession().getAttribute(UserContext.PERMISSION_IN_SESSION);

        //遍历系统菜单，与当前用户拥有的权限进行比对
        for(int i=0;i<menuList.size();i++){
            //这里采用正向遍历
            String menuPermission = menuList.get(i).getFunction();
            //菜单需要访问权限  当Str的length>0时，isNotBlank返回true
            if(StringUtils.isNotBlank(menuPermission)){
                //如果用户权限没有菜单权限，删除它，这样前台就不会显示
                if(!userPermissions.contains(menuPermission)){
                    menuList.remove(i);
                    //从前往后遍历，需要i-- 。因为该索引被删除前的元素的下一个元素占领，i--相当于回退遍历该元素
                    i--;
                }
                //else就说明该用户有权限，不需处理，菜单保留着
            }
            //else 说明该菜单根本不需要访问权限，谁都可以来访问，那么当前用户也可以，还是不做处理，菜单保留着

            //递归处理子菜单
            List<Menu> childrenMenuList = menuList.get(i).getChildren();
            if(!childrenMenuList.isEmpty()){
                checkMenuPermission(childrenMenuList);
            }
        }
    }
}
