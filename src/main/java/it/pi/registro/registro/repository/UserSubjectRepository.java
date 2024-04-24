package it.pi.registro.registro.repository;

import it.pi.registro.registro.entity.SubjectUser;
import it.pi.registro.registro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubjectRepository extends JpaRepository<SubjectUser, Long> {
}

