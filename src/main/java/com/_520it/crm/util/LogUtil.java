package com._520it.crm.util;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Log;
import com._520it.crm.service.ILogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class LogUtil {

    @Autowired
    private ILogService logService;

    /**
     * AOP日志记录，由于LogUtil已经交给spring容器管理，
     * 只要方法声明了joinPoint，Spring就会自动为我们装配好
     * @param joinpoint
     */
    public void writeLog(JoinPoint joinpoint){

        //防止AOP拦截logService,本身。造成死循环
        if(joinpoint.getTarget() instanceof ILogService){
            return;
        }

        Log log = new Log();
        log.setOptime(new Date());

        //如果这里拿到sessin就可以获取用户
        HttpServletRequest request = UserContext.get();
        Employee currentUser = (Employee)request.getAttribute(UserContext.USER_IN_SESSION);


        //让request来拿远程访问应用对应的ip地址
        log.setOpip(request.getRemoteAddr());

        //完整的function应该是 类名+方法名
        String className = joinpoint.getTarget().toString();
        Signature functionName = joinpoint.getSignature();
        log.setFunction(className+":"+functionName);

        //将请求参数转为JSON串
        ObjectMapper objectMapper = new ObjectMapper();
        String params = null;
        try {
            //把对象变成JSON串
            params = objectMapper.writeValueAsString(joinpoint.getArgs());
            log.setParams(params);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        //插入日志到数据库
        logService.insert(log);
    }


}
