package it.pi.registro.registro.repository;

import it.pi.registro.registro.entity.ApiKey;
import it.pi.registro.registro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
    @Query("select ak from ApiKey ak \n" +
            "where curdate() between date(ak.startDate)\n" +
            "and date(ak.endDate)\n" +
            "and ak.apiKey = :apiKey")
    List<ApiKey> findValidApiKeys(@Param("apiKey")String apiKey);
}

