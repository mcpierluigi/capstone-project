package pier.capstone.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pier.capstone.entities.User;

@Repository
public interface UserRepository extends JpaRepository <User, UUID>  {
	Optional<User> findUserByEmail(String email);
	Optional<User> findUserByUsername(String username);
}
