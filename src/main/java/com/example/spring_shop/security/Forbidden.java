package com.example.spring_shop.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Forbidden {

    @GetMapping("/403")
    public String error403(){
        return  "403";
    }
}
