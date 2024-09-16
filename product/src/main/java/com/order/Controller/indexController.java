package com.order.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
    indexController(){
        System.out.println("indexController 생성자");
    }

    @GetMapping("/index")
    void loginForm() {
        System.out.println("===> index 확인");
    }
}
