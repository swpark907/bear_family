package dragonb.bearfamily.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dragonb.bearfamily.backend.model.LedgerColumnKind;

public interface LedgerColumnKindRepository extends JpaRepository<LedgerColumnKind, Integer>{
    
}