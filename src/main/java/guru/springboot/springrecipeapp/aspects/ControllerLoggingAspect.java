package guru.springboot.springrecipeapp.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ControllerLoggingAspect {

    /**
     * @Before("guru.springboot.springrecipeapp.aspects.PointCutDeclaration.controllerMappings()") public void controllerLogging(JoinPoint joinPoint){
     * MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
     * Object[] objects= joinPoint.getArgs();
     * for (Object object : objects) {
     * log.info("Objects accessed"+object.getClass().getName());
     * }
     * log.info("@@@Logging Before the Execution of Controller Method.." + methodSignature);
     * }
     */


    @Around("guru.springboot.springrecipeapp.aspects.PointCutDeclaration.controllerMappings()")
    public Object controllerLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        long startTime = System.currentTimeMillis();
        log.info("@@@Logging Before the Execution of Controller Method.." + methodSignature + Arrays.toString(joinPoint.getArgs()));

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();

        long completionTime = endTime-startTime;

        log.info("@@@Logging After the Execution of Controller Method..Completed in.." + completionTime);

        return result;
    }
}

