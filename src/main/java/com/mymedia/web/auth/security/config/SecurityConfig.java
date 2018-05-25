package com.mymedia.web.auth.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mymedia.web.auth.JwtAuthFilter;
import com.mymedia.web.auth.JwtAuthenticationEntryPoint;
import com.mymedia.web.auth.JwtAuthenticationProvider;

@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(securedEnabled = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("com.mymedia.web.auth")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	@Autowired
	private JwtAuthenticationProvider jwtAuthenticationProvider;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthEntryPoint;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(jwtAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/login/**").permitAll()
				.antMatchers("/register/**").permitAll()
				.antMatchers("/account/**").authenticated()
				.anyRequest()
				.authenticated().and()
				.authorizeRequests()
//				.antMatchers("/login/Google").permitAll()
//				.antMatchers("/register/**").permitAll()
//				.antMatchers("/upload/**").hasAuthority("PUBLISHER")
//				.antMatchers("/playlist/**", "/consumer**").hasAuthority("CONSUMER")
//				.antMatchers("/album/**", "/song/**", "/author/**", "/genre/**").fullyAuthenticated()
				.and()
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint);

	}
}