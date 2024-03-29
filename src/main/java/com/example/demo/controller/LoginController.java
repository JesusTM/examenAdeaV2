package com.example.demo.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dao.UserRepository;
import com.example.demo.service.UserService;

@Controller
public class LoginController {
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
    private UserService userService;

	@GetMapping("/welcome")
	public String index() {
		return "welcome";
	}
	
	@GetMapping({"/","/login"})
	public String menu() {
		return "index";
	}
	
	@GetMapping("/change_password")
    public String showChangePasswordForm() {
        return "change_password";
    }
     
	@PostMapping("/change_password")
	public String processChangePassword(HttpServletRequest request, HttpServletResponse response,
	        Model model, RedirectAttributes ra,
	        @AuthenticationPrincipal Authentication authentication) throws ServletException {
	    User user = (User) authentication.getPrincipal();
	    com.example.demo.dao.entity.User userUpdate = 
                userRepository.findByNombre(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));
	     
	    String oldPassword = request.getParameter("oldPassword");
	    String newPassword = request.getParameter("newPassword");
	     
	    model.addAttribute("pageTitle", "Change Expired Password");
	     
	    if (oldPassword.equals(newPassword)) {
	        model.addAttribute("message", "Your new password must be different than the old one.");
	         
	        return "change_password";
	    }
	    
	    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
	    
	    if (!bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
	        model.addAttribute("message", "Your old password is incorrect.");          
	        return "change_password";
	         
	    } else {
	        userService.changePassword(userUpdate, newPassword);
	        request.logout();
	         
	        return "redirect:/welcome";          
	    }
	     
	}
}
