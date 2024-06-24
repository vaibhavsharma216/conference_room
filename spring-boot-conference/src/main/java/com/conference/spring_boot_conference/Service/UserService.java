package com.conference.spring_boot_conference.Service;

import com.conference.spring_boot_conference.Entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    User signUp(User user);
    User login(String email, String password);
}
