package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Admin;
import com.example.demo.model.Login;
import com.example.demo.repo.AdminRepository;
import com.example.demo.repo.LoginRepository;


@Service
public class MyUserDetailsService  implements UserDetailsService{
	 @Autowired
	 LoginRepository loginRepository;
	 @Autowired
	 AdminRepository adminRepository;  
	 @Autowired
	 BookController bookController; 
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		String username = null;
		String password = null;
		 if(!userName.contains("authenticate"))
		 {
			 System.out.println("not coming in authenticate..."+userName);

			 username =userName;
			 password="password";
			 
		 }
		 if(userName.contains("authenticate"))
		 {
			 System.out.println("bookController.userTypeData"+bookController.userTypeData);
			 
			 
			 
			 if(bookController.userTypeData.equals("admin")) {
				 System.out.println("coming in admin");

				 List<Admin> av=adminRepository.findByusernameAndPassword(bookController.authenticationCredentials.getUsername(),bookController.authenticationCredentials.getPassword());
				  username=av.get(0).getUsername();
				  password=av.get(0).getPassword(); 
			 }
if(bookController.userTypeData.equals("login")) {
	 System.out.println("coming in login");

	 List<Login> av=loginRepository.findByusernameAndPassword(bookController.authenticationCredentials.getUsername(),bookController.authenticationCredentials.getPassword());
	  username=av.get(0).getUsername();
	  password=av.get(0).getPassword(); 
				 
			 }
			
		 }
		
		 System.out.println("username "+username);
		 System.out.println("password "+password);

		return new User(username,password, new ArrayList<>());
	}

}
