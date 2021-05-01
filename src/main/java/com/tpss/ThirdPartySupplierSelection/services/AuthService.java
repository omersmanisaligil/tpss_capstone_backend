package com.tpss.ThirdPartySupplierSelection.services;

import com.tpss.ThirdPartySupplierSelection.dao.RoleDAO;
import com.tpss.ThirdPartySupplierSelection.dao.UserDAO;
import com.tpss.ThirdPartySupplierSelection.entity.Role;
import com.tpss.ThirdPartySupplierSelection.entity.User;
import com.tpss.ThirdPartySupplierSelection.entity.constants.ERole;
import com.tpss.ThirdPartySupplierSelection.payload.request.LoginRequest;
import com.tpss.ThirdPartySupplierSelection.payload.request.SignUpRequest;
import com.tpss.ThirdPartySupplierSelection.payload.response.JwtResponse;
import com.tpss.ThirdPartySupplierSelection.payload.response.MessageResponse;
import com.tpss.ThirdPartySupplierSelection.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserService userService;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest){
	Authentication authentication = authenticationManager.authenticate(
	new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	SecurityContextHolder.getContext().setAuthentication(authentication);
	String jwt = jwtUtils.generateJwtToken(authentication);

	User user = (User) authentication.getPrincipal();
	List<String> roles = user.getAuthorities().stream()
	.map(item -> item.getAuthority())
	.collect(Collectors.toList());

	return ResponseEntity.ok(new JwtResponse(jwt,
	user.getUserId(),
	user.getUsername(),
	user.getEmail(),
	roles));
    }

    public ResponseEntity<?> registerUser(SignUpRequest signUpRequest){
	Role adminRole = roleDAO.findByName(ERole.ROLE_ADMIN)
	.orElseThrow(() -> new RuntimeException("Error: Role admin is not found."));

	Role personnelRole = roleDAO.findByName(ERole.ROLE_PERSONNEL)
	.orElseThrow(() -> new RuntimeException("Error role personnel is not found"));

	Role userRole = roleDAO.findByName(ERole.ROLE_USER)
	.orElseThrow(() -> new RuntimeException("Error: Role user is not found."));

	if(userDAO.existsByUsername(signUpRequest.getUsername())) {
	    return ResponseEntity
	    .badRequest()
	    .body(new MessageResponse("Error: Username is already taken."));
	}
	if(userDAO.existsByEmail(signUpRequest.getEmail())) {
	    return ResponseEntity
	    .badRequest()
	    .body(new MessageResponse("Error: This email is already in use"));
	}

	User user = new User(signUpRequest.getUsername(),
	bCryptPasswordEncoder.encode(signUpRequest.getPassword()),
	signUpRequest.getEmail());

	/*Set<String> strRoles = signUpRequest.getRoles();
	Set<Role> roles = new HashSet<>();

	if (strRoles == null) {
	    roles.add(userRole);
	} else {
	    strRoles.forEach(role -> {
		switch (role) {
		    case "admin":
			roles.add(adminRole);
			roles.add(personnelRole);
			roles.add(userRole);
			break;
		    case "personnel":
			roles.add(personnelRole);
			roles.add(userRole);
		    default:
			roles.add(userRole);
		}
	    });
	}*/
	Set<Role> roles = userService.arrangeRoles(signUpRequest.getRole());
	user.setUserRoles(roles);
	userDAO.save(user);

	return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
