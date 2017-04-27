package com.mymedia.web.dto;

/**
 * Created by Nazar on 11.04.2017.
 */
public class UserBeanEntity {
    private int id;
    private String username;
    private int roleId;
    private String roleName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int role) {
        this.roleId = role;
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    
    
}
