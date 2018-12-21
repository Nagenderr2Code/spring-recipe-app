package guru.springboot.springrecipeapp.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ControllerLoggingAspect {

    @Before("guru.springboot.springrecipeapp.aspects.PointCutDeclaration.controllerMappings()")
    public void controllerLogging(JoinPoint joinPoint){
        MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
        Object[] objects= joinPoint.getArgs();
        for (Object object : objects) {
           log.info("Objects accessed"+object.getClass().getName());
        }
        log.info("@@@Logging Before the Execution of Controller Method.." + methodSignature);
    }


}
