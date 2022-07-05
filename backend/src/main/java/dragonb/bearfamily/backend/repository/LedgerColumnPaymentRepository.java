package dragonb.bearfamily.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dragonb.bearfamily.backend.model.LedgerColumnPayment;

public interface LedgerColumnPaymentRepository extends JpaRepository<LedgerColumnPayment, Integer>{
    
}
