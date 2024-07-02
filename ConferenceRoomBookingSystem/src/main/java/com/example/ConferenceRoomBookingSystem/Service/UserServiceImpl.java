package com.example.ConferenceRoomBookingSystem.Service;

import com.example.ConferenceRoomBookingSystem.Entity.User;
import com.example.ConferenceRoomBookingSystem.Entity.UserStatus;
import com.example.ConferenceRoomBookingSystem.GlobalExceptionHandling.CustomExceptions;
import com.example.ConferenceRoomBookingSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

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
        return userRepository.findByEmail(email).orElseThrow(()-> new CustomExceptions.ResourceNotFoundException("User not found with email: " + email));
    }
//        if (user == null) {
//            throw new CustomExceptions.ResourceNotFoundException("User not found with email: " + email);
//        }
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new CustomExceptions.BadRequestException("Invalid password");
//        }
//        return user;
//    }


    @Override
    public User deactivateUser(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setStatus(UserStatus.INACTIVE);
            return userRepository.save(user);
        }
        throw new CustomExceptions.ResourceNotFoundException("User not Present");
    }
}
