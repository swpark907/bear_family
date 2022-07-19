package dragonb.bearfamily.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dragonb.bearfamily.backend.model.terms.Terms;

public interface TermsRepository extends JpaRepository<Terms, Long>{
    
}
