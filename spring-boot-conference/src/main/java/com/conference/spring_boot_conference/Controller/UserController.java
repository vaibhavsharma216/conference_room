package com.conference.spring_boot_conference.Controller;

import com.conference.spring_boot_conference.Entity.User;
import com.conference.spring_boot_conference.Repository.UserRepository;
import com.conference.spring_boot_conference.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import java.util.List;

@RestController
@RequestMapping("/users")
//@Api(value = "User Management System", description = "Operations pertaining to users in the User Management System")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/signup")
//    @ApiOperation(value = "Sign up a new user")
    public User signUp(@RequestBody User user) {
        return userService.signUp(user);
    }

    @PostMapping("/login")
//    @ApiOperation(value = "Log in a user")
    public User login(@RequestParam String email, @RequestParam String password) {
        return userService.login(email, password);
    }
}
