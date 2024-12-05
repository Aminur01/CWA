package com.A1.cwa.utils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.A1.cwa.model.JwtRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * This class is to decode JWT token.
 */
@Component
public class JwtDecoder {

    private static final Logger logger = LoggerFactory.getLogger(JwtDecoder.class);

    /**
     * This method is to get the user details from the JWT token.
     *
     * @param token the JWT token
     * @return the JwtRequest object containing user details
     */
    public JwtRequest getJwtRequest(String token) {
        String[] parts = token.split("\\.");
        JwtRequest jwtRequest = new JwtRequest();

        logger.info("Retrieving userId and auditTrackingId from jwtToken.");

        if (parts.length == 3) {
            String payload = parts[1];
            byte[] decodedBytes = Base64Utils.decodeFromUrlSafeString(payload);
            String decodedPayload = new String(decodedBytes, StandardCharsets.UTF_8);

            String userId = JsonParser.parseString(decodedPayload).getAsJsonObject().get("sub").getAsString();
            String auditTrackingId = JsonParser.parseString(decodedPayload).getAsJsonObject().get("auditTrackingId").getAsString();
            String ou = JsonParser.parseString(decodedPayload).getAsJsonObject().get("ou").getAsString();
            JsonArray arrayRoles = JsonParser.parseString(decodedPayload).getAsJsonObject().get("cwaroles").getAsJsonArray();

            List<String> cwaRoles = new ArrayList<>();
            for (JsonElement role : arrayRoles) {
                cwaRoles.add(role.getAsString());
            }

            jwtRequest.setAuditTrackingId(auditTrackingId);
            jwtRequest.setUserId(userId);
            jwtRequest.setJwtRoles(cwaRoles);
            jwtRequest.setOu(ou);

            logger.info("Fetched userId: {}, auditTrackingId: {}, ou: {}, and jwtRoles: {} from jwtToken.", userId, auditTrackingId, ou, cwaRoles);

            return jwtRequest;
        }
        return null;
    }
}
