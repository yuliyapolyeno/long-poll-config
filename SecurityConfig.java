package com.bedgebury.presentation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("123456").roles("USER");
		auth.inMemoryAuthentication().withUser("creator").password("123456").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("creator").password("123456").roles("SUPERADMIN");
	}
	
	@Override
	public void configure (WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
 		
	    http.csrf().disable().authorizeRequests()
	    .antMatchers("/presentationRequest/**").permitAll()
	    .antMatchers("/presentationActive/**").permitAll()
	    .antMatchers("/presentation/**").access("hasRole('ROLE_ADMIN')")
	    .antMatchers("/login/**").permitAll()
//	    .anyRequest().anonymous()
	    .and()
	    		.formLogin().loginPage("/login").failureUrl("/login?error")
	    		.usernameParameter("username").passwordParameter("password")
	    		.defaultSuccessUrl("/presentation", true)
	    .and()
	    		.logout().logoutSuccessUrl("/login?logout");		
	}
			
}
