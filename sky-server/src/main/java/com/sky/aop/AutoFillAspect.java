package com.sky.aop;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointcut() {
    }

    @Before("autoFillPointcut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("开始进行公共字段自动填充");

        // 获取到当前被拦截的方法上的数据库操作操作类型
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();   // 方法签名对象
        AutoFill autoFill = methodSignature.getMethod().getAnnotation(AutoFill.class);  // 获得方法上的注解对象
        OperationType operationType = autoFill.value();                                 // 获得数据库操作类型

        log.info("当前操作类型：{}", operationType);

        // 获得到当前被拦截方法的参数--实体对象
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) return;

        Object entity = args[0];    // 不能使用具体类型，因为传入的类型是不确定的

        // 根据数据库操作类型，为对应的属性通过反射来赋值
        switch (operationType) {
            case INSERT:
                // 插入操作类型，为4个公共字段赋值
                log.info("开始进行插入操作，公共字段填充");
                try {
                    // setCreateTime
                    entity.getClass().getMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class).invoke(entity, LocalDateTime.now());
                    // setCreateUser
                    entity.getClass().getMethod(AutoFillConstant.SET_CREATE_USER, Long.class).invoke(entity, BaseContext.getCurrentId());
                    // setUpdateTime
                    entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class).invoke(entity, LocalDateTime.now());
                    // setUpdateUser
                    entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_USER, Long.class).invoke(entity, BaseContext.getCurrentId());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case UPDATE:
                // 更新操作类型，为2个公共字段赋值
                log.info("开始进行更新操作，公共字段填充");
                try {
                    // setUpdateTime
                    entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class).invoke(entity, LocalDateTime.now());
                    // setUpdateUser
                    entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_USER, Long.class).invoke(entity, BaseContext.getCurrentId());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                break;
        }
    }
}
