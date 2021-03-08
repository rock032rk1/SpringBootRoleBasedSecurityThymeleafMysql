package com.springbootrolebasedsecurity.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.springbootrolebasedsecurity.app.service.UserDetalisServiceImpl;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetalisServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder encodePassword() {
		return new BCryptPasswordEncoder();
	}
	
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(encodePassword());
		authProvider.setUserDetailsService(userDetailsService());
		
		return authProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

         auth.authenticationProvider(authProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
          
         http.csrf().disable()
             .authorizeRequests()
             .antMatchers("/userPage","/saveUser").permitAll()
             .antMatchers("/","/login").hasAnyAuthority("USER","ADMIN","EDITOR")
             .antMatchers("/new").hasAnyAuthority("ADMIN", "EDITOR")
             .antMatchers("/updateP").hasAnyAuthority("ADMIN", "EDITOR")
             .antMatchers("/delete").hasAuthority("ADMIN")
             .anyRequest().authenticated()
             .and()
             .formLogin().loginPage("/login").permitAll()
             .and()
             .logout().invalidateHttpSession(true)
      		 .clearAuthentication(true)
      		 .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
      		 .logoutSuccessUrl("/login").permitAll()
             .and()
             .exceptionHandling().accessDeniedPage("/403");
             
//             .logout().permitAll()
//             .and()
//             .exceptionHandling().accessDeniedPage("/403");
         
         
//         .logout().invalidateHttpSession(true)
// 		.clearAuthentication(true)
// 		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
// 		.logoutSuccessUrl("/login").permitAll();
	}
}
