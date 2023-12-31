package com.securityTest.loginApplication.filter;

import com.securityTest.loginApplication.dao.impl.UserDao;
import com.securityTest.loginApplication.entity.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

public class RandomFilter extends OncePerRequestFilter {


    @Autowired
    private UserDao userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession();

        printAllSessionData(session);
        printAllRequestData(request);

        // Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
        SecurityContextImpl securityContext =  (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");

        UserEntity userEntity = (UserEntity)session.getAttribute("user");

        String  userName = securityContext.getAuthentication().getName();
        UserEntity userEntity = userRepository.getUser(userName);

       session.setAttribute("user", userEntity);


        filterChain.doFilter(request, response);


     //   request.getParameter("");



    }

    private static void printAllRequestData(HttpServletRequest request) {
        Enumeration<String> paramters =  request.getParameterNames();

        while(paramters.hasMoreElements()){
            String key = paramters.nextElement();
            System.out.print(key) ;
            System.out.println(" : " + request.getParameter(key));
        }
    }

    private static void printAllSessionData(HttpSession session) {
        Enumeration<String> attributeNames = session.getAttributeNames();
        while(attributeNames.hasMoreElements()){
            String key = attributeNames.nextElement();
            System.out.print("Session key : " + key);
            System.out.println("value  : " + session.getAttribute(key));
        }
    }
}
