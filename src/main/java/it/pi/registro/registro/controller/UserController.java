package it.pi.registro.registro.controller;

import it.pi.registro.registro.dto.request.UserCreateRequestDTO;
import it.pi.registro.registro.dto.request.UserInfoRequestDTO;
import it.pi.registro.registro.dto.response.UserDataResponseDTO;
import it.pi.registro.registro.entity.User;
import it.pi.registro.registro.mapper.UserMapper;
import it.pi.registro.registro.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDataResponseDTO>> getAllUsers(){

        return new ResponseEntity<>(userService.getAllUsers()
                .stream()
                .map(userMapper::toDataResponseDTO)
                .collect(Collectors.toList()), HttpStatus.OK);

    }

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(@RequestBody UserInfoRequestDTO userInfoRequestDTO){

        try{
            return new ResponseEntity<>(userService.getUserInfoByEmail(userInfoRequestDTO), HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId){
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/findByMail")
    public ResponseEntity<User> getUserByEmail(@RequestBody String email){
        User user = userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Create simple user without details
     */
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    /**
     * Create user with details
     */
    @PostMapping("/withDetails")
    public ResponseEntity<User> createUserWithDetails(@Valid @RequestBody UserCreateRequestDTO userCreateRequestDTO){
        User savedUser = userService.createUserWithDetails(userCreateRequestDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId ,@RequestBody User user){
        User updatedUser = userService.getUserById(userId);
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        userService.updateUser(updatedUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
