package dragonb.bearfamily.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dragonb.bearfamily.backend.model.Ledger;

public interface LedgerRepository extends JpaRepository<Ledger, Long>{
    
}
