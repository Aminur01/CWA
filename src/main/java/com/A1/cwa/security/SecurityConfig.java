package com.A1.cwa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Value("${spring.application.name}")
	private String url_contains_for_auth;

	/**
	 * Method to configure the default security filter chain and adding the URL list
	 * to be authenticated.
	 *
	 * @param http HttpSecurity configuration object
	 * @return SecurityFilterChain object
	 * @throws Exception if an error occurs during configuration
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and().csrf().disable()
				.addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class).cors().and()
				.authorizeRequests().antMatchers("/url_contains_for_auth/**").authenticated().anyRequest().permitAll();

		return http.build();
	}

	/**
	 * Bean to disable the default AuthenticationManager.
	 *
	 * @return AuthenticationManager that throws an exception
	 */
	// @Bean
	public AuthenticationManager noopAuthenticationManager() {
		return authentication -> {
			throw new AuthenticationServiceException("Authentication is disabled!");
		};
	}

	/*
	 * The code snippet you provided is from a Spring Security configuration class
	 * and is an excellent example of method chaining. Let's break it down step by
	 * step:
	 * 
	 * 1. http.sessionManagement() This starts by configuring the session management
	 * for your application. .sessionCreationPolicy(SessionCreationPolicy.NEVER):
	 * Sets the session creation policy to NEVER, meaning the framework will never
	 * create an HttpSession, but it will use one if it already exists. 
	 * 2. .and()
	 * .and() is used to chain another configuration block. It indicates that you're
	 * done configuring session management and want to continue with the next
	 * security configuration. 
	 * 3. .csrf().disable() .csrf(): Accesses the Cross-Site
	 * Request Forgery (CSRF) configuration. .disable(): Disables CSRF protection.
	 * 4. .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
	 * .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class):
	 * Adds the jwtAuthenticationFilter before the BasicAuthenticationFilter in the
	 * filter chain. This is typically used to add custom filters in the security
	 * filter chain. 
	 * 5. .cors() .cors(): Enables Cross-Origin Resource Sharing
	 * (CORS). It’s typically used when your frontend is hosted on a different
	 * domain than your backend. 
	 * 6. .and() Another .and() is used to chain the next
	 * configuration block. 
	 * 7. .authorizeRequests() .authorizeRequests(): Begins the
	 * configuration for authorizing HTTP requests. 
	 * 8.
	 * .antMatchers("/url_contains_for_auth/").authenticated()**
	 * .antMatchers("/url_contains_for_auth/**").authenticated(): Specifies that any
	 * request matching the given pattern (in this case, URLs that contain
	 * /url_contains_for_auth/) should be authenticated. 
	 * 9.	 * .anyRequest().permitAll() .anyRequest().permitAll(): Specifies that all other
	 * requests should be permitted without authentication. 
	 * 10. return http.build();
	 * 
	 * Finally, the method returns the built SecurityFilterChain object. Method
	 * Chaining Explained: Each method in this chain is called on the http object,
	 * and every method returns the http object itself (or a closely related
	 * configuration object), which allows the chaining of subsequent methods. The
	 * use of .and() is critical as it signals the end of a particular configuration
	 * block and allows for the chaining of another block. The overall configuration
	 * is concise and readable because of method chaining, allowing multiple aspects
	 * of security to be configured in a single statement. This pattern is commonly
	 * used in Spring Security to configure various security-related concerns
	 * fluently and efficiently.
	 */
}
