package dragonb.bearfamily.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dragonb.bearfamily.backend.model.Refreshtoken;

public interface RefreshtokenRepository extends JpaRepository<Refreshtoken, Long>{
    Optional<Refreshtoken> findByUserIdentity(String UserIdentity);

    Optional<Refreshtoken> findByTokenAndGuid(String token, String guid);
}
