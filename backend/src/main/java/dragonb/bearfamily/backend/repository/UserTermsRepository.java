package dragonb.bearfamily.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dragonb.bearfamily.backend.model.UserTerms;
import dragonb.bearfamily.backend.model.UserTermsId;

public interface UserTermsRepository extends JpaRepository<UserTerms, UserTermsId> {
    
}
