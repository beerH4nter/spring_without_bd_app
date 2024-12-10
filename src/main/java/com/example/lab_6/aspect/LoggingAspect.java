package com.example.lab_6.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Указываем, что аспект должен срабатывать на всех методах в пакетах controller
    @Pointcut("execution(* com.example.lab_6.controllers.*.*(..))")
    public void controllerMethods() {}

    @Around("controllerMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // Логируем входящие параметры
        Object[] args = joinPoint.getArgs();
        logger.info("Entering method: {} with arguments: {}", joinPoint.getSignature(), args);

        // Выполняем метод
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            logger.error("Exception in method: {}", joinPoint.getSignature(), throwable);
            throw throwable;
        }

        // Логируем время выполнения и возвращаемый результат
        long timeTaken = System.currentTimeMillis() - startTime;
        logger.info("Exiting method: {} with result: {}. Execution time: {} ms",
                joinPoint.getSignature(), result, timeTaken);

        return result;
    }
}
