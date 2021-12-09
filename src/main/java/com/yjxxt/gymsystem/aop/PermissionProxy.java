package com.yjxxt.gymsystem.aop;

import com.yjxxt.gymsystem.annotation.RequiredPermission;
import com.yjxxt.gymsystem.exceptions.NoAuthException;
import com.yjxxt.gymsystem.exceptions.NoLoginException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
@Aspect
public class PermissionProxy {


    @Autowired
    private HttpSession session;

    @Around(value="@annotation(com.yjxxt.gymsystem.annotation.RequiredPermission)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //判断是否登录
        List<String> permissions = (List<String>) session.getAttribute("permissions");
        if(permissions == null || permissions.size()==0){
            throw new NoLoginException("未登录");
        }
        //判断是否有访问目标资源的权限码
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        RequiredPermission requiredPermission = methodSignature.getMethod().getDeclaredAnnotation(RequiredPermission.class);
        //比对
        if(!(permissions.contains(requiredPermission.code()))){
            throw  new NoAuthException("无权限访问");
        }
        Object result = pjp.proceed();

        return result;
    }
}
