package com.conference.spring_boot_conference.Repository;

import com.conference.spring_boot_conference.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    User findByEmail(String email);
}
