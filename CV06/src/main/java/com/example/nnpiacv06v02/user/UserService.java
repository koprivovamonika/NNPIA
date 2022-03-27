package com.example.nnpiacv06v02.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    com.example.nnpiacv06v02.user.UserRepository userRepository;

    BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
     super();
     this.userRepository = userRepository;
     this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
