package com._520it.crm.web.interceptor;

import com._520it.crm.domain.Employee;
import com._520it.crm.util.PermissionUtil;
import com._520it.crm.util.UserContext;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Handler;

/**
 * HandlerInterceptor是springMVC项目中的拦截器，它拦截的目标是请求的地址，比MethodInterceptor先执行。
 * HandlerInterceptor拦截的是请求地址，所以针对请求地址做一些验证、预处理等操作比较合适。
 */
public class LoginInterceptor implements HandlerInterceptor {

    /***
     * 在控制器执行之前完成业务逻辑操作 也就是DispatcherServlet
     * 方法的返回值决定逻辑是否继续执行。true代表继续执行；false代表不再继续执行
     * @param httpServletRequest
     * @param httpServletResponse
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {

        /*为当前线程绑定request对象*/
        UserContext.set(httpServletRequest);

        /* 登陆请求 /login 已配置不经过拦截器，没有往线程里放request */


        //------------------拦截登陆---------------------
        // 在springmvc配置文件中放行/login
        //从session中获取用户信息
        Employee employee = (Employee)httpServletRequest.getSession().getAttribute(UserContext.USER_IN_SESSION);
        if(employee ==null){
            //拦截请求并且重定向到登陆页面
            httpServletResponse.sendRedirect("/ssm-crm/login.jsp");
            //System.out.println("loginEmployee:"+employee);
            return false;
        }
        //-----------------权限校验---------------------
        /**
         * HandlerMethod封装了很多属性，在访问请求方法的时候可以方便的访问到方法、方法参数、
         * 方法上的注解、所属类等并且对方法参数封装处理，也可以方便的访问到方法参数的注解等信息。
         *
         * 把请求变成权限表达式？（路径+类名+方法） handler.getClass: HandlerMethod
         * com._520it.crm.web.controller.xxxController:list
         */
        if(handler instanceof HandlerMethod){
            HandlerMethod methodObj = (HandlerMethod) handler;
            String function = methodObj.getBean().getClass().getName()+":"+methodObj.getMethod().getName();
            //System.out.println("LoginInterceptor.preHandle():function:"+function);
            //判断当前用户是否有权限访问这个方法
            boolean flag = PermissionUtil.checkPermission(function);
            if(flag){
                return true;
            }else{
                if(methodObj.getMethod().isAnnotationPresent(ResponseBody.class)){
                    //如果是ajax
                    httpServletResponse.sendRedirect("/ssm-crm/noPermission.json");
                }else{
                    //若果是页面，重定向到noPermission.jsp
                    httpServletResponse.sendRedirect("/ssm-crm/noPermission.jsp");

                }
                return false;
            }

        }

        return true;
    }

    /*在控制器执行完毕之后执行的逻辑操作*/
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /*在完成视图渲染之后，执行此方法。*/
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
