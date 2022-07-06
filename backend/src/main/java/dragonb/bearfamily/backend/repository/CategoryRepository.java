package dragonb.bearfamily.backend.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import dragonb.bearfamily.backend.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    Optional<Category> findByIdAndUserIdentityOrUserIdentityIsNull(Long id, String userIdentity);

    List<Category> findAllByUserIdentityOrUserIdentityIsNull(String userIdentity);

    @Transactional
    void deleteByIdAndUserIdentity(Long id, String userIdentity);
}
