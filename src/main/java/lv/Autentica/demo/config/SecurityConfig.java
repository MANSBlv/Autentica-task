package lv.Autentica.demo.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.Getter;
import lombok.Setter;
import lv.Autentica.demo.service.UserService;
import lv.Autentica.demo.service.impl.UserServiceImpl;

@Getter
@Setter
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserService userService;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserServiceImpl();
	}
	

	
	@Bean
	public BCryptPasswordEncoder passwordEndcoder() {
		return new BCryptPasswordEncoder();
	}

	
	@Bean
	public AuthenticationManager authManager (AuthenticationConfiguration authConfig) throws Exception{
		return authConfig.getAuthenticationManager();
	}
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider(){ // to authenticate a username and password
	      DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
	      auth.setUserDetailsService(userDetailsService());
	      auth.setPasswordEncoder(passwordEndcoder());
	      return auth;
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(authenticationProvider());
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		//for user request validation
		http.authorizeRequests().antMatchers("/items/addNew","/update/**")
		.hasAuthority("ADMIN").anyRequest().authenticated()
		.and().formLogin().permitAll().and().logout().permitAll();
		return http.build();
		}

}
