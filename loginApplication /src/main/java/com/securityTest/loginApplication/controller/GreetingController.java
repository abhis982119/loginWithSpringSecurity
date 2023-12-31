package com.securityTest.loginApplication.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

@RestController
public class GreetingController {
/*
                    x
  ["userName, age, height]
 */
    @GetMapping("/greet")
    public String greet(@RequestParam(required = false) String firstName, HttpSession session){
        Enumeration<String> attributes =  session.getAttributeNames();
        while (attributes.hasMoreElements()){
            String key =    attributes.nextElement();

            System.out.println("key" + key);
            System.out.println(session.getAttribute(key));
        }

       // Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
        SecurityContextImpl securityContext =  (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");

        if (firstName == null) {
           firstName =  securityContext.getAuthentication().getName();
        }
        return "Hello, Mr. " + firstName;
    }

}
