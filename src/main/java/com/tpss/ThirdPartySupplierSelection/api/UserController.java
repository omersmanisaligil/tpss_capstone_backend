package com.tpss.ThirdPartySupplierSelection.api;

import com.tpss.ThirdPartySupplierSelection.entity.User;
import com.tpss.ThirdPartySupplierSelection.payload.request.LoginRequest;
import com.tpss.ThirdPartySupplierSelection.payload.request.SignUpRequest;
import com.tpss.ThirdPartySupplierSelection.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(path="")
    public ResponseEntity<Page<User>> getAll(
    @RequestParam(name="page", defaultValue = "0") int page,
    @RequestParam(name="size", defaultValue = "3") int size
    //,@RequestParam(name="sort", defaultValue = "id") String[] sort
    ){
	Page<User> allUsers = userService.getAll(page,size);
        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }

    /*@GetMapping(path="/getOne")
    public ResponseEntity<User> getOneByID(@RequestParam("id") Long id){
        User user = userService.getOneByID(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }*/

    @GetMapping(path="/{username}")
    public ResponseEntity<User> getOneByUsername(@PathVariable("username") String username){
        User user = userService.getOneByUsername(username).get();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    @PostMapping(path="/add")
    public ResponseEntity<?> addUser(@Validated @NonNull @RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path="/edit/{id}")
    public ResponseEntity<?> updateUserByID(@Validated @NonNull @RequestBody User user,
					@PathVariable("id") Long id){
        User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(currentUser.getUserId() == id)
            return userService.updateUserByID(user, id);
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

   @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
       User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(currentUser.getUserId() == id) {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
   }
}
