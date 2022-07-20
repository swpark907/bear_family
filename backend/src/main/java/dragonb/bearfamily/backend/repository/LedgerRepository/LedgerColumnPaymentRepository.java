package dragonb.bearfamily.backend.repository.LedgerRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import dragonb.bearfamily.backend.model.ledger.LedgerColumnPayment;

public interface LedgerColumnPaymentRepository extends JpaRepository<LedgerColumnPayment, Integer>{
    
}
