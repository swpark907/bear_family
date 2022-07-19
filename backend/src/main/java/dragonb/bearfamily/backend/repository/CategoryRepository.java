package dragonb.bearfamily.backend.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import dragonb.bearfamily.backend.model.category.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{;
    List<Category> findAllByUserIdentityOrUserIdentityIsNull(String userIdentity);

    @Transactional
    void deleteByIdAndUserIdentity(Long id, String userIdentity);
}
