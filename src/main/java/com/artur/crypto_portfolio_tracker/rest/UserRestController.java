package com.artur.crypto_portfolio_tracker.rest;

import com.artur.crypto_portfolio_tracker.dto.UserDTO;
import com.artur.crypto_portfolio_tracker.entity.User;
import com.artur.crypto_portfolio_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> findAllUsers(){
        return userService.findAllAndConvertToDTO();
    }

    //TODO: exception handle for cases where user is not founded
    @GetMapping("/{userId}")
    @PreAuthorize("#userId == authentication.principal.id or hasRole('ADMIN')")
    public UserDTO getUserInfo(@PathVariable int userId){

        User theUser = userService.findById(userId);
        UserDTO userResponse = new UserDTO();

        userResponse.setUserName(theUser.getUserName());
        userResponse.setEnabled(theUser.getEnabled());
        userResponse.setId(theUser.getId());

        return userResponse;
    }

    // TODO: password must be encrypted before saving
    @PostMapping()
    public UserDTO createNewUser(@RequestBody User user){

        user.setId(0);
        user.setEnabled(1);
        User dbUser = userService.save(user);

        UserDTO userResponse = new UserDTO();

        userResponse.setUserName(dbUser.getUserName());
        userResponse.setEnabled(dbUser.getEnabled());
        userResponse.setId(dbUser.getId());

        return userResponse;
    }
}
