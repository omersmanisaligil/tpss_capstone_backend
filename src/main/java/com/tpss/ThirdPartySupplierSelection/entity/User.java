package com.tpss.ThirdPartySupplierSelection.entity;

import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="USERS")
public class User implements UserDetails {

    @Id @NonNull @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;
    @NonNull @Column(name="username")
    private String username;
    @NonNull @Column(name="password")
    private String password;
    @NonNull @Column(name="email")
    private String email;
    @Transient
    private String role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="USER_ROLE_MAP",
    joinColumns = {@JoinColumn(name="USER_ID")},
    inverseJoinColumns = {@JoinColumn(name="ROLE_ID")})
    private Set<Role> userRoles=new HashSet<Role>();

    @OneToMany(mappedBy="owner", orphanRemoval = true, cascade=CascadeType.PERSIST )
    private Set<Order> orders=new HashSet<Order>();

    @NonNull @Transient
    private Collection<? extends GrantedAuthority> grantedAuthorities;


    @Column(name="NONEXPIRED")
    private final boolean isAccountNonExpired=true;

    @Column(name="NONLOCKED")
    private final boolean isAccountNonLocked=true;

    @Column(name="CREDNONEXPIRED")
    private final boolean isCredentialsNonExpired=true;

    @NonNull @Column(name="ENABLED")
    private final boolean isEnabled=true;

    public User() {}

    public User(Long userId, String username, String password, String email) {
	this.userId = userId;
	this.username = username;
	this.password = password;
	this.email = email;
    }
    public User(String username, String password, String email) {
	this.username = username;
	this.password = password;
	this.email = email;
    }

    public User(Long userId, String username, String email, String password, List<GrantedAuthority> grantedAuthorities) {
	this.userId = userId;
	this.username = username;
	this.password = password;
	this.email = email;
	this.grantedAuthorities=grantedAuthorities;
    }

    public Long getUserId() {
	return userId;
    }

    public void setUserId(Long userId) {
	this.userId = userId;
    }

    public String getUsername() {
	return username;
    }

    @Override
    public boolean isAccountNonExpired() {
	return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
	return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
	return isEnabled;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	return grantedAuthorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> grantedAuthorities){
        this.grantedAuthorities=grantedAuthorities;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Set<Role> getUserRoles() {
	return userRoles;
    }

    public void setUserRoles(Set<Role> userRoles) {
	this.userRoles = userRoles;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }
}
