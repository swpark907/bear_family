package dragonb.bearfamily.backend.repository;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dragonb.bearfamily.backend.model.User;
 
@Repository
public interface UserRepository extends JpaRepository<User, String> {
	public List<User> findAllByOrderByIdDesc();
}