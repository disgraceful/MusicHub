package com.mymedia.web.dto;

public class UserBeanEntity {
    private String id;
    private String username;
    private String email;
    private String roleId;
    private String roleName;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String role) {
        this.roleId = role;
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    
    
}
