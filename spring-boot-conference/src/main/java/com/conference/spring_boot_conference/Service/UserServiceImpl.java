package com.conference.spring_boot_conference.Service;

import com.conference.spring_boot_conference.Entity.User;
//import com.conference.spring_boot_conference.GlobalExceptionHandling.CustomExceptions;
import com.conference.spring_boot_conference.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
    public class UserServiceImpl implements UserService {

        @Autowired
        private UserRepository userRepository;

//        @Autowired
//        private PasswordEncoder passwordEncoder;

        @Override
        public User signUp(User user) {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }


        @Override
        public User login(String email, String password) {
            return userRepository.findByEmail(email);
        }
//        if (user == null) {
//            throw new CustomExceptions.ResourceNotFoundException("User not found with email: " + email);
//        }
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new CustomExceptions.BadRequestException("Invalid password");
//        }
//        return user;
//    }
}
