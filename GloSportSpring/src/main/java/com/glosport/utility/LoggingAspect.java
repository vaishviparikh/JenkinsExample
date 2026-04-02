package com.glosport.utility;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.Logger;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    //Add necessary annotations to define this class as an Aspect and configure pointcuts for service layer methods
    //Implement the logging logic in the following methods

    @Before("execution(* com.glosport.service.PlayerServiceImpl.*(..)) || execution(* com.glosport.service.EquipmentServiceImpl.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        // Log the service method name before execution in INFO level
        logger.info("Executing service method: {}", joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* com.glosport.service.PlayerServiceImpl.*(..)) || execution(* com.glosport.service.EquipmentServiceImpl.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        // Log the service method name and returned result after successful execution in INFO level
        logger.info("Completed Service method: {}"+joinPoint.getSignature().getName()+" with result: "+result);
    }

    @AfterThrowing(pointcut = "execution(* com.glosport.service.PlayerServiceImpl.*(..)) || execution(* com.glosport.service.EquipmentServiceImpl.*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Exception error) {
        // Log the service method name and exception message after an exception is thrown in ERROR level
        logger.info("Exception Thrown by Service Method: "+joinPoint.getSignature().getName()+" with error message:  "+error.getMessage());
    }


}
