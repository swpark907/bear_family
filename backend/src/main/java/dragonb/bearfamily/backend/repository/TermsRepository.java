package dragonb.bearfamily.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dragonb.bearfamily.backend.model.Terms;

public interface TermsRepository extends JpaRepository<Terms, Integer>{
    
}
