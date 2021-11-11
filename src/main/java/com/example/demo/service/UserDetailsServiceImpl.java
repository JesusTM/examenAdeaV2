package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      com.example.demo.dao.entity.User registerUser = 
                 userRepository.findByNombre(username).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));
		
      List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
      GrantedAuthority grantedAuthority = new GrantedAuthority() {
		@Override
		public String getAuthority() {
			return "ROLE_ADMIN";
		}
	  };
      grantList.add(grantedAuthority);
      
      UserDetails user = (UserDetails) new User(registerUser.getNombre(), registerUser.getPassword(), true,
    		  true, registerUser.isPasswordExpired(), false, grantList);
      return user;
    }
}
