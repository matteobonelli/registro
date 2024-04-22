package it.pi.registro.registro.repository;

import it.pi.registro.registro.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
    UserType findByType(String type);
}

