package com.A1.cwa.model;

import java.util.List;

public class JwtRequest {
	
	private String userId;
	private String auditTrackingId;
	private List<String> jwtRoles;
	private String ou;
	
	
	
	public JwtRequest() {
		super();
	}
	
	public JwtRequest(String userId, String auditTrackingId, List<String> jwtRoles, String ou) {
		super();
		this.userId = userId;
		this.auditTrackingId = auditTrackingId;
		this.jwtRoles = jwtRoles;
		this.ou = ou;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAuditTrackingId() {
		return auditTrackingId;
	}
	public void setAuditTrackingId(String auditTrackingId) {
		this.auditTrackingId = auditTrackingId;
	}
	public List<String> getJwtRoles() {
		return jwtRoles;
	}
	public void setJwtRoles(List<String> jwtRoles) {
		this.jwtRoles = jwtRoles;
	}
	public String getOu() {
		return ou;
	}
	public void setOu(String ou) {
		this.ou = ou;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JwtRequest [userId=");
		builder.append(userId);
		builder.append(", auditTrackingId=");
		builder.append(auditTrackingId);
		builder.append(", jwtRoles=");
		builder.append(jwtRoles);
		builder.append(", ou=");
		builder.append(ou);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	

}
