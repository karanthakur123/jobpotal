package com.example.demo.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	  @Autowired
	  private MyUserDetailsService userDetailsService;
	  @Autowired
	  private JwtUtil jwtUtil;
	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain chain)
	
		throws ServletException,IOException{
			final String authorization=request.getHeader("Authorization");
			
			String username=null;
			String jwt =null;
			
			if(authorization !=null && authorization.startsWith("Bearer "))
			{
				jwt=authorization.substring(7);
				try {
				username=jwtUtil.extractUsername(jwt);
				    }
				catch(Exception e)
				{
					response.getWriter().print("Incorrect token");;	
				}
			}
             if((authorization ==null || !authorization.startsWith("Bearer ")) && !request.getRequestURL().toString().contains("authenticate") && !request.getRequestURL().toString().contains("register"))
			{
				response.getWriter().print("Please enter valid token");
			}
			if(request.getRequestURL().toString().contains("authenticate") || request.getRequestURL().toString().contains("register"))
			{
				
			}
			if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null && !request.getRequestURL().toString().contains("authenticate"))
			{
				
				
				System.out.println("coming....");
				UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);
				if(jwtUtil.validateToken(jwt, userDetails))
				{
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken
		            (userDetails,null,userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
					.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
				
				
				 		
			}
			chain.doFilter(request, response);
	
		}
	}
