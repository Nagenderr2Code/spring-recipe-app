package guru.springboot.springrecipeapp.aspects;


import guru.springboot.springrecipeapp.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ServiceLoggingAspect {

    @AfterReturning(pointcut = "guru.springboot.springrecipeapp.aspects.PointCutDeclaration.serviceLogging()", returning = "result")
    public void serviceLogging(JoinPoint joinPoint, Object result){

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info("Aspect Logging For Service" + methodSignature.getName());
    }

    @AfterThrowing(pointcut = "guru.springboot.springrecipeapp.aspects.PointCutDeclaration.serviceLogging()", throwing = "ex")
    public void logException(JoinPoint joinPoint, NotFoundException ex){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String args = Arrays.toString(joinPoint.getArgs());
        log.error("Logging from Aspect.." + ex.getMessage() + methodSignature.toString() + args);
    }
}
