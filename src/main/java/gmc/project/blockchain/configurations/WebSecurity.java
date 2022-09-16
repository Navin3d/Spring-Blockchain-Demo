package gmc.project.blockchain.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import gmc.project.blockchain.filters.AuthenticationFilter;
import gmc.project.blockchain.filters.AuthorizationFilter;
import gmc.project.blockchain.services.AuthService;

@Configuration
public class WebSecurity {
	
	private AuthenticationManager authManger;
	
	@Autowired
	private AuthConfig authConfig;
	@Autowired
	private AuthService authService;
	@Autowired
	private AuthorizationFilter authorizationFilter;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Bean
	public SecurityFilterChain getFilterChain(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
		auth.userDetailsService(authService).passwordEncoder(bCryptPasswordEncoder);
		authManger = auth.build();
		
		http.authorizeRequests()
			.antMatchers("/login", "/").permitAll()
			.anyRequest().authenticated()
		.and()
			.authenticationManager(authManger);
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilter(getAuthFilter());
		return http.build();
	}
	
	private AuthenticationFilter getAuthFilter() throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(authConfig, authService, authManger);
		authenticationFilter.setFilterProcessesUrl(authConfig.getAuthUrl());
		return authenticationFilter;
	}

}
