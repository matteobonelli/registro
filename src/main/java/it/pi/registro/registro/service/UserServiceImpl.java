package it.pi.registro.registro.service;

import it.pi.registro.registro.dto.UserCreateDTO;
import it.pi.registro.registro.entity.User;
import it.pi.registro.registro.entity.UserDetail;
import it.pi.registro.registro.entity.UserType;
import it.pi.registro.registro.enums.UserTypeEnum;
import it.pi.registro.registro.repository.UserRepository;
import it.pi.registro.registro.repository.UserTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private UserTypeRepository userTypeRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User createUserWithDetails(UserCreateDTO userCreateDTO) {

        User user = new User();
        user.setFirstName(userCreateDTO.getFirstName());
        user.setLastName(userCreateDTO.getLastName());
        user.setEmail(userCreateDTO.getEmail());
        user.setPassword(userCreateDTO.getPassword());

        UserDetail userDetail = new UserDetail();
        userDetail.setAddress(userCreateDTO.getAddress());
        userDetail.setCity(userCreateDTO.getCity());

        UserType userType = userTypeRepository.findByType(UserTypeEnum.GUEST.toString());

        if(userCreateDTO.getType() != null){
            userType = userTypeRepository.findByType((userCreateDTO.getType().toString()));
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
