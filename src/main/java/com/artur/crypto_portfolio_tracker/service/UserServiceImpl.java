package com.artur.crypto_portfolio_tracker.service;

import com.artur.crypto_portfolio_tracker.dao.UserRepository;
import com.artur.crypto_portfolio_tracker.dto.UserDTO;
import com.artur.crypto_portfolio_tracker.entity.Asset;
import com.artur.crypto_portfolio_tracker.entity.Role;
import com.artur.crypto_portfolio_tracker.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    public UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUserName(String username) {

        User theUser = userRepository.findUserByUserName(username);
        if(theUser==null){
            throw new UsernameNotFoundException("username not found " + username);
        }

        return theUser;
    }

    @Override
    public User findById(int userId) {
        Optional<User> result = userRepository.findById(userId);
        User theUser = null;

        if(result.isPresent()){
            theUser = result.get();
        }
        else{
            throw new RuntimeException("User not found id: "+ userId);
        }

        return theUser;
    }

    @Override
    @Transactional
    public User save(User user) {
        User dbUser = userRepository.save(user);
        return dbUser;
    }

    @Override
    public List<UserDTO> findAllAndConvertToDTO() {
        List<User> allUsers = userRepository.findAll();
        List<UserDTO> dtoUsers = new ArrayList<>();

        for(User user: allUsers){
            UserDTO userDTO = new UserDTO();

            userDTO.setUserName(user.getUserName());
            userDTO.setEnabled(user.getEnabled());
            userDTO.setId(user.getId());

            dtoUsers.add(userDTO);
        }

        return dtoUsers;
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


    public List<User> findAll(){
        return userRepository.findAll();
    }



    @Override
    @Transactional
    public List<Asset> giveAssetsByUserId(int userId) {
        Optional<User> result = userRepository.findById(userId);
        User theUser = null;

        if(result.isPresent()){
            theUser = result.get();
        }
        else{
            throw new RuntimeException("User not found id: "+ userId);
        }

        List<Asset> assets = theUser.getAssets();

        return assets;
    }

}
