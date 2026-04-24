package com.artur.crypto_portfolio_tracker.service;

import com.artur.crypto_portfolio_tracker.dao.UserRepository;
import com.artur.crypto_portfolio_tracker.entity.Role;
import com.artur.crypto_portfolio_tracker.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    public UserRepository userRepository;

    @Override
    public User findByUserName(String username) {

        User theUser = userRepository.findUserByUserName(username);
        if(theUser==null){
            throw new UsernameNotFoundException("username not found " + username);
        }

        return theUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User theUser = findByUserName(username);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        theUser.getRoles().forEach(role->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(
                theUser.getUserName(),
                theUser.getPassword(),
                authorities
        );
    }

}
