package it.pi.registro.registro.service.impl;

import it.pi.registro.registro.dto.request.UserCreateRequestDTO;
import it.pi.registro.registro.dto.request.UserInfoRequestDTO;
import it.pi.registro.registro.dto.response.UserInfoResponseDTO;
import it.pi.registro.registro.entity.User;
import it.pi.registro.registro.entity.UserDetail;
import it.pi.registro.registro.entity.UserType;
import it.pi.registro.registro.enums.UserTypeEnum;
import it.pi.registro.registro.repository.UserRepository;
import it.pi.registro.registro.repository.UserTypeRepository;
import it.pi.registro.registro.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserTypeRepository userTypeRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User createUserWithDetails(UserCreateRequestDTO userCreateRequestDTO) {

        User user = new User();
        user.setFirstName(userCreateRequestDTO.getFirstName());
        user.setLastName(userCreateRequestDTO.getLastName());
        user.setEmail(userCreateRequestDTO.getEmail());
        user.setPassword(userCreateRequestDTO.getPassword());

        UserDetail userDetail = new UserDetail();
        userDetail.setAddress(userCreateRequestDTO.getAddress());
        userDetail.setCity(userCreateRequestDTO.getCity());

        UserType userType = userTypeRepository.findByType(UserTypeEnum.GUEST.toString());

        if (userCreateRequestDTO.getType() != null) {
            userType = userTypeRepository.findByType((userCreateRequestDTO.getType().toString()));
        }

        user.setUserDetail(userDetail);
        user.setUserType(userType);
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.get();
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public UserInfoResponseDTO getUserInfoByEmail(UserInfoRequestDTO userInfoRequestDTO) {

        User user = userRepository.findByEmail(userInfoRequestDTO.getEmail());
        return new UserInfoResponseDTO(user.getFirstName(), user.getLastName(), user.getUserType().getDescription());
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {

        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
