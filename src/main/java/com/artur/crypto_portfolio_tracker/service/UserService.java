package com.artur.crypto_portfolio_tracker.service;

import com.artur.crypto_portfolio_tracker.dto.UserDTO;
import com.artur.crypto_portfolio_tracker.entity.Asset;
import com.artur.crypto_portfolio_tracker.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {
    public User findByUserName(String username);
    public User findById(int userId);
    public User save(User user);
    public List<UserDTO> findAllAndConvertToDTO();
    List<User> findAll();
    List<Asset> giveAssetsByUserId(int userId);
}
