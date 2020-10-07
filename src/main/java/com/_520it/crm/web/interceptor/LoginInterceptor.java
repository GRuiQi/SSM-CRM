package com._520it.crm.web.interceptor;

import com._520it.crm.domain.Employee;
import com._520it.crm.util.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    /***
     * 在控制器执行之前完成业务逻辑操作 也就是DispatcherServlet
     * 方法的返回值决定逻辑是否继续执行。true代表继续执行；false代表不再继续执行
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //拦截登陆
        Employee employee = (Employee)httpServletRequest.getSession().getAttribute(UserContext.USER_IN_SESSION);
        if(employee ==null){
            //拦截请求并且重定向到登陆页面
            httpServletResponse.sendRedirect("/ssm-crm/login.jsp");
            System.out.println(employee);
            return false;
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
