package com.example.ConferenceRoomBookingSystem.Service;

import com.example.ConferenceRoomBookingSystem.Entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    User signUp(User user);
    User login(String email, String password);
    User deactivateUser(String email);
}

