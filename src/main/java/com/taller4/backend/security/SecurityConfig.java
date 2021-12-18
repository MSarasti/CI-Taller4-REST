package com.taller4.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {       
        http
        	.cors().disable()
        	.csrf().disable()
        	.authorizeRequests()
        	.antMatchers("/").authenticated()
        	.antMatchers("/secure/**").authenticated()
        	.antMatchers("/users/**").permitAll()
        	.antMatchers("/api/**").permitAll()
        	.antMatchers("/admin/**").hasRole("ADMIN")
        	.antMatchers("/productcategory/**").hasRole("ADMIN")
        	.antMatchers("/productsubcategory/**").hasRole("ADMIN")
        	.antMatchers("/unitmeasure/**").hasRole("ADMIN")
        	.antMatchers("/product/**").hasRole("ADMIN")
        	.antMatchers("/workorder/**").hasRole("ADMIN")
        	.antMatchers("/workorderrouting/**").hasRole("ADMIN")
        	.antMatchers("/operator/**").hasRole("OPERATOR")
        	.antMatchers("/specialoffer/**").hasRole("OPERATOR")
        	.antMatchers("/specialofferproduct/**").hasRole("OPERATOR")
        	.antMatchers("/salesorderdetail/**").hasRole("OPERATOR")
        	.anyRequest().authenticated()
        	.and().httpBasic()
        	.and().formLogin()
            	.loginProcessingUrl("/login")
				.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }
}
