package com.cyh.aop;

import com.alibaba.fastjson.JSON;
import com.cyh.annotation.OperateLogAnnotation;
import com.cyh.model.OperateLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Aspect
@Component
public class LogAspect {

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.cyh.annotation.OperateLogAnnotation)")
    public void pointcut(){}

    /**
     * 环绕通知
     * 环绕通知=前置+目标方法执行+后置通知
     */
    @Around("pointcut()")
    public Object doAround(final ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("环绕通知开始");
        long time = System.currentTimeMillis();
        Object proceed = joinPoint.proceed(); // proceed方法就是用于启动目标方法执行的，在此之前相当于前置通知，之后相当于后置通知
        time = System.currentTimeMillis() - time;
        // 添加日志
        MethodSignature signature = (MethodSignature)joinPoint.getSignature(); // 获取被增强的方法相关信息
        OperateLog olog = new OperateLog();
        olog.setRunTime(time);
        olog.setReturnValue(JSON.toJSONString(proceed));
        olog.setId(UUID.randomUUID().toString());
        olog.setArgs(JSON.toJSONString(joinPoint.getArgs()));
        olog.setCreateTime(new Date());
        olog.setMethod(signature.getDeclaringTypeName()+ "." + signature.getName());
        OperateLogAnnotation annotation = signature.getMethod().getAnnotation(OperateLogAnnotation.class);
        if(annotation != null){
            olog.setLevel(annotation.level());
        }
        olog.setOperationType(annotation.operateType().getValue());
        olog.setOperationUnit(annotation.operateUnit().getValue());
        System.out.println("记录日志：" + olog);
        return proceed;
    }
}
