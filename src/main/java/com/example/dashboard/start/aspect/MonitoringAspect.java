package com.example.dashboard.start.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MonitoringAspect {

    @Around("execution(* com.example.dashboard.start.service..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - start;
            log.info(
                    "Service={} Method={} ExecutionTime={}ms",
                    joinPoint.getTarget().getClass().getSimpleName(),
                    joinPoint.getSignature().getName(),
                    executionTime
            );
            return result;
        } catch (Exception ex) {
            log.error(
                    "Service={} Method={} Error={}",
                    joinPoint.getTarget().getClass().getSimpleName(),
                    joinPoint.getSignature().getName(),
                    ex.getMessage(),
                    ex
            );
            throw ex;
        }
    }
}