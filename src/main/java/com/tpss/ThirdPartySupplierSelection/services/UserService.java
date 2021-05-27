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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserDAO userDAO,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDAO=userDAO;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    public Page<User> getAll(int page,
                             int size){
        Pageable pageRequest = PageRequest.of(page, size);
        Page<User> users = userDAO.findAll(pageRequest);

        return users;
    }

    public Optional<User> getOneByID(Long id){
        return userDAO.findById(id);
    }

    public Optional<User> getOneByUsername(String username){
        return userDAO.findByUsername(username);
    }

    public ResponseEntity<?> updateUserByID(User updatedUser,
                                     Long id){
        User existingUser = userDAO.findById(id).get();

        Set<Role> roles=arrangeRoles(updatedUser.getRole());
        updatedUser.setUserRoles(roles);

        StringBuilder resp=new StringBuilder("");

        if(!existingUser.getUsername().equals(updatedUser.getUsername())) {
            resp.append("Sorry, username cannot be changed\n");
            updatedUser.setUsername(existingUser.getUsername());
        }

        String newEmail=updatedUser.getEmail();
        if(userDAO.existsByEmail(newEmail) && !existingUser.getEmail().equals(newEmail)) {
            resp.append("This email is already in use\n");
            updatedUser.setEmail(existingUser.getEmail());
        }

        existingUser=updatedUser;
        existingUser.setUserId(id);

        existingUser.setPassword(bCryptPasswordEncoder.encode(existingUser.getPassword()));

        userDAO.save(existingUser);
        return ResponseEntity.ok(new MessageResponse(resp+"User updated")).of(Optional.of(existingUser));
    }

    public void deleteUser(Long id) {
        userDAO.deleteById(id);
    }

    public void addUser(User user){
        userDAO.save(user);
    }

    public Set<Role> arrangeRoles(String roleStr){
        Role adminRole = roleDAO.findByName(ERole.ROLE_ADMIN)
        .orElseThrow(() -> new RuntimeException("Error: Role admin is not found."));

        Role personnelRole = roleDAO.findByName(ERole.ROLE_PERSONNEL)
        .orElseThrow(() -> new RuntimeException("Error role personnel is not found"));

        Role userRole = roleDAO.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role user is not found."));

        Set<Role> roles = new HashSet<>();

        //String roleStr = user.getRole();

        if (roleStr == null) {
            roles.add(userRole);
        } else {
            switch (roleStr.toLowerCase()) {
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
            }
        return roles;
    }
}
