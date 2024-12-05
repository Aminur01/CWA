package com.A1.cwa.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.A1.cwa.model.JwtRequest;

public class JWTAuthentication implements Authentication {


	private static final long serialVersionUID = 1L;
	private final JwtRequest model;
	private final List<? extends GrantedAuthority> authorities;
	private boolean authenticated;

	public JWTAuthentication(JwtRequest model) {
		this.model = model;
		this.authorities = Collections.emptyList();
		this.authenticated = true;
	}

	public JWTAuthentication(JwtRequest model, List<? extends GrantedAuthority> authorities) {
		this.model = model;
		this.authorities = authorities;
		this.authenticated = true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public JwtRequest getCredentials() {
		// TODO Auto-generated method stub
		return model;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return model.getUserId();
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		this.authenticated = authenticated;

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return model.getUserId();
	}

}
