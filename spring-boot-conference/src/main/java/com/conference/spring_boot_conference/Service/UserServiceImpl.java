package com.conference.spring_boot_conference.Service;

import com.conference.spring_boot_conference.Entity.User;
import com.conference.spring_boot_conference.Entity.UserStatus;
import com.conference.spring_boot_conference.GlobalExceptionHandling.CustomExceptions;
import com.conference.spring_boot_conference.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.jasypt.util.text.AES256TextEncryptor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
    public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AES256TextEncryptor textEncryptor;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public User signUp(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String encryptedPassword = textEncryptor.encrypt(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }


//    @Override
//    public User login(String email, String password) {
////            return userRepository.findByEmail(email).orElseThrow(()-> new CustomExceptions.ResourceNotFoundException("User not found with email: " + email));
//        User user = userRepository.findByEmail(email);
//        if (user == null) {
//            throw new CustomExceptions.ResourceNotFoundException("User not found with email: " + email);
//        }
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new CustomExceptions.BadRequestException("Invalid password");
//        }
//        return user;
//User user = userRepository.findByEmail(email)
//        .orElseThrow(() -> new CustomExceptions.ResourceNotFoundException("User not found with email: " + email));
//    }

//    @Override
//    public User login(String email, String password) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new CustomExceptions.ResourceNotFoundException("User not found with email: " + email));
//
//        if (!password.equals(user.getPassword())) {
//            throw new CustomExceptions.BadRequestException("Incorrect password");
//        }
//        return user;
//    }


      @Override
      public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomExceptions.ResourceNotFoundException("User not found with email: " + email));

        String decryptedPassword = textEncryptor.decrypt(user.getPassword());
          if (!decryptedPassword.equals(password)) {
              throw new RuntimeException("Invalid email or password");
          }

          return user;

         }




    @Override
    public User deactivateUser(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setStatus(UserStatus.INACTIVE);
            return userRepository.save(user);
        }
        throw new CustomExceptions.ResourceNotFoundException("User not Present in the System");
    }
}
