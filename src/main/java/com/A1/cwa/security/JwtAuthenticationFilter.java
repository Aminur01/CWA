package com.A1.cwa.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.A1.cwa.model.JwtRequest;
import com.A1.cwa.utils.JwtDecoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtDecoder jwtDecoder;
    @Autowired
    AuthorizationComponent authorizationComponent;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

		try {
			String jwt = extractJwtFromRequest(request);

			if (jwt != null) {

				JwtRequest jwtRequest = jwtDecoder.getJwtRequest(jwt);
				Authentication authentication = new JWTAuthentication(jwtRequest);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				//log.info("After setting authentication: {}", SecurityContextHolder.getContext().getAuthentication());

			}

		} catch (Exception ex) {
			log.error("Exception occurred while Authentication: {}", ex.getMessage());
		}

		filterChain.doFilter(request, response);
		this.resetAuthenticationAfterRequest();
	}



    private void resetAuthenticationAfterRequest() {
		SecurityContextHolder.getContext().setAuthentication(null);
		
	}



	/**
     * Extract JWT token from request headers
     * 
     * @param request the HTTP request
     * @return the JWT token if present, otherwise null
     */
    private String extractJwtFromRequest(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        return null;
    }

	
	
}

