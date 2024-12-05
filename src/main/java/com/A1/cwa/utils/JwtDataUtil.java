package com.A1.cwa.utils;

import org.springframework.stereotype.Component;

import com.A1.cwa.model.JwtRequest;

@Component
public class JwtDataUtil {

	/**
	 * Fetches the user ID from the given bearer token.
	 *
	 * @param bearerToken the bearer token
	 * @return the user ID if present, otherwise null
	 */
	public String fetchUserIdFromBearerToken(String bearerToken) {

		JwtDecoder decoder = new JwtDecoder();
		JwtRequest jwtRequest = decoder.getJwtRequest(bearerToken);

		return !jwtRequest.getUserId().isBlank() ? jwtRequest.getUserId() : null;
	}

	/**
	 * Fetches the registrar entity from the given JWT request.
	 *
	 * @param jwtRequest the JWT request
	 * @return the registrar entity if present, otherwise null
	 */
	public static String fetchRegistrarEntity(JwtRequest jwtRequest) {

		return jwtRequest.getJwtRoles().get(0).split(" ")[0];

	}
}
