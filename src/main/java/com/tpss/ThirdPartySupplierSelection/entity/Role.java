package com.tpss.ThirdPartySupplierSelection.entity;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tpss.ThirdPartySupplierSelection.entity.constants.ERole;

@Entity
@Table(name="ROLES")
public class Role {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="ROLE_ID")
    private Long role_id;

    @Enumerated(EnumType.STRING)
    @Column(name="role_name", length=20)
    private ERole name;

    public Role() {

    }
    public Role(ERole name) {
	this.name=name;
    }

    public Role(ERole name, Set<Permission> permissions) {
	this.name=name;
	this.permissions=permissions;
    }

    public Long getRole_id() {
	return role_id;
    }
    public void setRole_id(Long role_id) {
	this.role_id = role_id;
    }
    public ERole getName() {
	return name;
    }
    public void setName(ERole name) {
	this.name = name;
    }

    public Set<Permission> getPermissions() {
	return permissions;
    }
    public void setPermissions(Set<Permission> permissions) {
	this.permissions = permissions;
    }
    @JsonIgnore
    public Set<User> getUsers() {
	return users;
    }
    public void setUsers(Set<User> users) {
	this.users = users;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="ROLE_PERMISSION_MAP",
    joinColumns = {@JoinColumn(name="ROLE_ID")},
    inverseJoinColumns = {@JoinColumn(name="PERMISSION_ID")})
    private Set<Permission> permissions;

    @ManyToMany(fetch=FetchType.LAZY, mappedBy="userRoles")
    private Set<User> users=new HashSet<User>();
}
