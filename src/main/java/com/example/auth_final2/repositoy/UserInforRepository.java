package com.example.auth_final2.repositoy;

import com.example.auth_final2.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInforRepository  extends JpaRepository<UserInfo , Long> {
    Optional<UserInfo> findByName(String username);
}
