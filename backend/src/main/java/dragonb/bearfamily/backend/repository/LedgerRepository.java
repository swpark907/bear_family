package dragonb.bearfamily.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dragonb.bearfamily.backend.model.Ledger;

public interface LedgerRepository extends JpaRepository<Ledger, Long>{
    Optional<Ledger> findByIdAndUserIdentity(Long id, String userIdentity);

    List<Ledger> findAllByUserIdentity(String userIdentity);
}
