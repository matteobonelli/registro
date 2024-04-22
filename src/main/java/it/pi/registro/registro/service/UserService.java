package it.pi.registro.registro.service;

import it.pi.registro.registro.dto.UserCreateDTO;
import it.pi.registro.registro.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User createUserWithDetails(UserCreateDTO userCreateDTO);

    User getUserById(Long userId);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long userId);
}
