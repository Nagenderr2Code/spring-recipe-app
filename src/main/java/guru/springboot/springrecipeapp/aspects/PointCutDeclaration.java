package guru.springboot.springrecipeapp.aspects;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class PointCutDeclaration {


    // Converter Point cut Declaration
    @Pointcut("execution(* guru.springboot.springrecipeapp.converter.*.*(..))")
    public void converterLogging(){}

    // Controller Point cut Declaration
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

    // Service Point cut Declaration
    @Pointcut("execution(* guru.springboot.springrecipeapp.services.implementation.*.*(..))")
    public void serviceLogging(){}
}
