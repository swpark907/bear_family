package dragonb.bearfamily.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dragonb.bearfamily.backend.model.common.Refreshtoken;

public interface RefreshtokenRepository extends JpaRepository<Refreshtoken, Long>{
    Optional<Refreshtoken> findByUserIdentity(String UserIdentity);

    Optional<Refreshtoken> findByTokenAndUuid(String token, String uuid);
}
