package com.artur.crypto_portfolio_tracker.service;

import com.artur.crypto_portfolio_tracker.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    public User findByUserName(String username);
}
