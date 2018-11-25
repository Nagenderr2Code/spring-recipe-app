package guru.springboot.springrecipeapp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"", "/", "index", "index.html"})
public class IndexController {


    public String index() {

        return "index";
    }

}
