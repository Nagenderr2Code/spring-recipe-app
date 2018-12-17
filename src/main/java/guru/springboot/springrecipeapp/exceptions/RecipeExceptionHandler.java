package guru.springboot.springrecipeapp.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class RecipeExceptionHandler {

    @ExceptionHandler
    public ModelAndView handleCustomException(NotFoundException exception) {

        log.error("NotFoundException Occured.." + exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }


    @ExceptionHandler
    public ModelAndView handleException(NumberFormatException exception) {

        log.error("Exception Occured.." + exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(HttpStatus.BAD_REQUEST);
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }
}
