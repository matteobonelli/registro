package it.pi.registro.registro.service;

import it.pi.registro.registro.entity.UserType;
import it.pi.registro.registro.repository.UserTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserTypeServiceImpl implements UserTypeService{

    private UserTypeRepository userTypeRepository;

    @Override
    public UserType createUserType(UserType userType) {
        return userTypeRepository.save(userType);
    }

    @Override
    public UserType getUserTypeById(Long userTypeId) {
        return null;
    }

    @Override
    public List<UserType> getAllUserTypes() {
        return userTypeRepository.findAll();
    }

    @Override
    public UserType updateUserType(UserType userType) {
        return null;
    }

    @Override
    public void deleteUserType(Long userTypeId) {

    }
}
