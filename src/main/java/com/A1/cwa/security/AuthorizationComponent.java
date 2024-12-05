package com.A1.cwa.security;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.A1.cwa.exception.AuthorizationException;
import com.A1.cwa.utils.RoleUtil;
import com.A1.cwa.utils.Constants;

/**
 * Authorization class to check user access for services.
 */
@Component("AuthorizationComponent")
public class AuthorizationComponent {

	@Autowired
	private RoleUtil utility;

	private static Logger logger = LoggerFactory.getLogger(AuthorizationComponent.class);

	/**
	 * This method is responsible for checking user access to API
	 *
	 * @param serviceName The name of the service
	 * @return true if the user has access to the service, false otherwise
	 */
	public boolean checkUserAccess(String serviceName) {
		try {
			logger.info(
					"checkUserAccess Initiating with roles authorization for the user to access the api service: {}",
					serviceName);

			// Get the authentication object
			JWTAuthentication authentication = (JWTAuthentication) SecurityContextHolder.getContext()
					.getAuthentication();

			// Fetch authenticated roles
			Map<String, List<String>> authenticatedRoles = utility.getAuthenticatedRoles();

			// Fetch JWT roles
			List<String> jwtRoles = authentication.getCredentials().getJwtRoles();

			// Checking JWT roles with the valid available roles for the user to access the
			// service
			for (String role : jwtRoles) {
				if (authenticatedRoles.get(role) != null && authenticatedRoles.get(role).contains(serviceName)) {
					logger.info("checkUserAccess User has access to perform: {}", serviceName);
					return true;
				}
			}
		} catch (Exception e) {
			logger.error("Error in Parsing and mapping roles", e);
		}
		logger.error("Error Check access : {}", serviceName);
		throw new AuthorizationException(Constants.USER_NOT_ATHORIZED);
	}
}
