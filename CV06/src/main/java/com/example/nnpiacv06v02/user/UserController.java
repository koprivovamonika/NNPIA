package com.example.nnpiacv06v02.user;

import com.example.nnpiacv06v02.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/1.0/users")
    GenericResponse createUser(@Valid @RequestBody User user){
        userService.save(user);
        return new GenericResponse("user saved");
    }
}
