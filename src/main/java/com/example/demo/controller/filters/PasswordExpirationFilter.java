package com.example.demo.controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.dao.entity.User;

@Component
public class PasswordExpirationFilter implements Filter {
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
         
        if (isUrlExcluded(httpRequest)) {
            chain.doFilter(request, response);
            return;
        }
         
        System.out.println("PasswordExpirationFilter");
 
        User user = getLoggedInUser();
         
        if (user != null && user.isPasswordExpired()) {
                showChangePasswordPage(response, httpRequest, user);           
        } else {
            chain.doFilter(httpRequest, response);         
        }
         
    }
    
    private boolean isUrlExcluded(HttpServletRequest httpRequest)
            throws IOException, ServletException {
        String url = httpRequest.getRequestURL().toString();
         
        if (url.endsWith(".css") || url.endsWith(".png") || url.endsWith(".js")
                || url.endsWith("/change_password")) {
            return true;
        }
         
        return false;
    }     
    
    private User getLoggedInUser() {
        Authentication authentication
            = SecurityContextHolder.getContext().getAuthentication();
        Object principal = null;
         
        if (authentication != null) {
            principal = authentication.getPrincipal();
        }
         
        if (principal != null && principal instanceof User) {
            User user = (User) principal;
            return user;
        }
         
        return null;
    }
    
    private void showChangePasswordPage(ServletResponse response,
            HttpServletRequest httpRequest, User user) throws IOException {         
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String redirectURL = httpRequest.getContextPath() + "/change_password";
        httpResponse.sendRedirect(redirectURL);
    }
}
