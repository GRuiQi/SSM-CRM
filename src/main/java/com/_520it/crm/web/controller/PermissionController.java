package com._520it.crm.web.controller;

import com._520it.crm.page.PageResult;
import com._520it.crm.query.PermissionQueryObject;
import com._520it.crm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/permission")
    public String index(){
        return "permission";
    }

    @ResponseBody
    @RequestMapping("/permission_list")
    public PageResult list(PermissionQueryObject qo){
        PageResult result = null;
        result = permissionService.queryForPage(qo);
        return result;
    }

    @ResponseBody
    @RequestMapping("/permission_queryByRid")
    public PageResult queryByRid(PermissionQueryObject queryObject){
        return permissionService.queryForPage(queryObject);
    }
}
