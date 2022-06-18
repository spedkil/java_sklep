package com.example.spring_shop.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping//(path="api/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping("/register")
    public String signUpForm(Model model){
        model.addAttribute("user", new UserDto());
        System.out.println("halo");
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String register(UserDto user){
        try{
            registrationService.register(user);
        }catch (IllegalStateException e){
            return "email_taken";
        }

        return "register_success";
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        registrationService.confirmToken(token);
        return "token_confirmed";
    }
}
