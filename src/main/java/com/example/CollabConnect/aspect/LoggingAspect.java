package com.example.CollabConnect.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.CollabConnect.service.*.*(..))")
    public void LogBeforeMethodExecution(JoinPoint joinPoint){
        System.out.println("Executing method: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.example.CollabConnect.service.*.*(..))")
    public void LogAfterMethodExecution(JoinPoint joinPoint)
    {
        System.out.println("Method execution: " + joinPoint.getSignature().getName());
    }

    @Around("execution(* com.example.CollabConnect.service.*.*(..))")
    public Object LogExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long start = System.currentTimeMillis();

        // Proceed with the method execution
        Object result = proceedingJoinPoint.proceed();

        long elapsedTime = System.currentTimeMillis() - start;

        System.out.println("Execution time of " + proceedingJoinPoint.getSignature().getName() +
                ": " + elapsedTime + " ms");

        return result;
    }
}
