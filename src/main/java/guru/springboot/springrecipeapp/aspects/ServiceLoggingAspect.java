package guru.springboot.springrecipeapp.aspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ServiceLoggingAspect {

    @AfterReturning(pointcut = "guru.springboot.springrecipeapp.aspects.PointCutDeclaration.serviceLogging()", returning = "result")
    public void serviceLogging(Object result){
        log.info("Aspect Logging For Service" + result.toString());
    }
}
