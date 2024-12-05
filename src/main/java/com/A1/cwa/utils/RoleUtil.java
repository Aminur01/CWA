package com.A1.cwa.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.A1.cwa.model.JwtRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is to have common methods to retrieve roles, registrar entity,
 * and check registrar.
 */
@Component
public class RoleUtil {

    @Value("${cwa.roles.path}")
    private String authenticatedCwaRoles;

    private static Logger logger = LoggerFactory.getLogger(RoleUtil.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Reads role-based URLs from a JSON file.
     *
     * @return a map of roles and their corresponding URLs
     * @throws JsonProcessingException if there is a problem processing the JSON
     */
    public Map<String, List<String>> getAuthenticatedRoles() throws JsonProcessingException {
    	
        String data = readJsonUrls(authenticatedCwaRoles);
        return objectMapper.readValue(data, Map.class);
    }

    /**
     * Reads JSON URLs from a file path.
     *
     * @param jsonFilePath the path to the JSON file
     * @return the content of the JSON file as a string
     */
    public String readJsonUrls(String jsonFilePath) {
        String data = "";
        try {
            data = Files.readString(Paths.get(jsonFilePath));
        } catch (IOException e) {
            logger.error("Error while getting authenticated URLs", e);
        }
        return data;
    }

    /**
     * Reads JSON content from a file.
     *
     * @param filePath the path to the file
     * @return the content of the file as a string
     * @throws IOException if there is a problem reading the file
     */
    public String readFromFilePath(String filePath) throws IOException {
        Path file = Path.of(filePath);
        return Files.readString(file, StandardCharsets.UTF_8);
    }

    /**
     * Checks if the registrar entity matches based on roles.
     *
     * @param jwtRequest the JWT request containing user roles
     * @param registrarEntity the registrar entity to check
     * @return true if the registrar entity matches, false otherwise
     */
    public boolean checkRegistrarEntity(JwtRequest jwtRequest, String registrarEntity) {
        logger.info("Checking registrar entity based on jwt role");
        List<String> cwaRoles = jwtRequest.getJwtRoles();
        for (String role : cwaRoles) {
            if (role.contains(registrarEntity)) {
                logger.info("Registrar entity found in role: {}", role);
                return true;
            }
        }
        logger.info("Registrar entity not found in any roles.");
        return false;
    }

    /**
     * Gets the registrar entity based on roles.
     *
     * @param jwtRoles the JWT roles
     * @return the registrar entity
     * @throws Exception 
     * @throws AuthorizationException if no valid registrar entity is found
     */
    public String getRegistrarEntity(List<String> jwtRoles) throws Exception {
        logger.info("Setting registrar entity based on jwt role");
        for (String role : jwtRoles) {
            if (role.contains("CBF") || role.contains("CBL") || role.contains("LUX")) {
                return role.split(" ")[0];
            }
        }
        logger.error("Registrar entity is not among CBF, CBL, or LUX. Hence not authorized to access.");
        throw new Exception("User is not authorized for any registrar entity.");
    }
}
