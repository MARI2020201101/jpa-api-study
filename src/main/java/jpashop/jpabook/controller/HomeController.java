package jpashop.jpabook.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log
public class HomeController {

    @RequestMapping("/")
    public String home(){
        log.info("Welcome Home Controller");
        return "home";
    }
}
