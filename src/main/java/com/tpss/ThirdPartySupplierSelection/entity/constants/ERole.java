package com.tpss.ThirdPartySupplierSelection.entity.constants;

public enum ERole {
    ROLE_USER("USER"),
    ROLE_PERSONNEL("PERSONNEL"),
    ROLE_ADMIN("ADMIN");

    private String roleName;

    private ERole(String roleName) {
	this.roleName = roleName;
    }

    public String getRoleName() {
	return roleName;
    }

    public void setRoleName(String roleName) {
	this.roleName = roleName;
    }
}
