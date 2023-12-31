package com.securityTest.loginApplication.controller;

import com.securityTest.loginApplication.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignUp(@RequestParam String username, @RequestParam String password) {


        userRegistrationService.saveUser(username, password);

        // Redirect to the login page after successful signup
        return "redirect:/login?signup";
    }
}
