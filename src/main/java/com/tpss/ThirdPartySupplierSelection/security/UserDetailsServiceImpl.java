package com.tpss.ThirdPartySupplierSelection.security;

import com.tpss.ThirdPartySupplierSelection.dao.UserDAO;
import com.tpss.ThirdPartySupplierSelection.entity.Permission;
import com.tpss.ThirdPartySupplierSelection.entity.Role;
import com.tpss.ThirdPartySupplierSelection.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user = userDAO.findByUsername(username)
	                   .orElseThrow(() -> new UsernameNotFoundException("User not found with username" + username));

        return grantAuthorities(user);
    }

    public static User grantAuthorities(User user){
	List<GrantedAuthority> authorities=new ArrayList<>();
	Set<Role> userRoles=user.getUserRoles();

	for(Role r:userRoles) {
	    Set<Permission> rolePermissions=r.getPermissions();

	    for(Permission p:rolePermissions) {
		authorities.add(new SimpleGrantedAuthority(p.getName().toString()));
	    }
	}

	return new User(
	user.getUserId(),
	user.getUsername(),
	user.getEmail(),
	user.getPassword(),
	authorities);
    }
}
