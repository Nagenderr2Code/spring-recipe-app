package guru.springboot.springrecipeapp.aspects;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class PointCutDeclaration {

    @Pointcut("execution(* guru.springboot.springrecipeapp.converter.*.*(..))")
    public void converterLogging(){}

    @Pointcut("execution(* guru.springboot.springrecipeapp.controllers.*.*(..))")
    public void controllerMethods(){}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMapping(){}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping(){}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping(){}

    @Pointcut("(controllerMethods())&& (getMapping() || postMapping() || requestMapping())")
    public void controllerMappings(){}
}
