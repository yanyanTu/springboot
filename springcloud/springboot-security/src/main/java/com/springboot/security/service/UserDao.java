package com.springboot.security.service;

import com.springboot.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    /**
     * 查询用户信息
     * @param username
     * @return
     */
    User findByUsername(String username );
}
