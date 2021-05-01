package com.tpss.ThirdPartySupplierSelection.entity.constants;

public enum EPermission {
    ADMIN_READ("ADMIN_READ"),
    ADMIN_WRITE("ADMIN_WRITE"),
    PERSONNEL_READ("PERSONNEL_READ"),
    PERSONNEL_WRITE("PERSONNEL_WRITE"),
    USER_READ("USER_READ"),
    USER_WRITE("USER_WRITE");


    private String permission;

    EPermission(String permission){
	this.setPermission(permission);
    }

    public String getPermission() {
	return permission;
    }

    public void setPermission(String permission) {
	this.permission = permission;
    }
}
