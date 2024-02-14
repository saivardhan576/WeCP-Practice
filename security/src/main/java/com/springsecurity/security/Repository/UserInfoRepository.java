package com.springsecurity.security.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.security.Entity.UserInfo;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String username);

}
