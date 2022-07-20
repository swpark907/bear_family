package dragonb.bearfamily.backend.repository.LedgerRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import dragonb.bearfamily.backend.model.ledger.LedgerColumnKind;

public interface LedgerColumnKindRepository extends JpaRepository<LedgerColumnKind, Integer>{
    
}
