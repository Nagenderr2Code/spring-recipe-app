package guru.springboot.springrecipeapp.aspects.converteraspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ConverterLoggingAspect {

    @Before("guru.springboot.springrecipeapp.aspects.PointCutDeclaration.converterLogging()")
    public void logBeforeConverting(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object[] objects = joinPoint.getArgs();
        if (objects.length > 0) {
            String[] classNames = new String[objects.length];
            int index = 0;
            for (Object object : objects) {
                if (object instanceof TypeDescriptor) {
                    classNames[index] = ((TypeDescriptor) object).getName();
                    index++;
                }
            }
            if (classNames.length > 0) {
                log.info("Aspect Logging for Converter From Class..[" + classNames[0] + "] To Class [" + classNames[1] + "]");
            }
        }
    }
}
