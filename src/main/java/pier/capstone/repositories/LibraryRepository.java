package pier.capstone.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pier.capstone.entities.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library, UUID> {
	Library findLibraryByUsername(String username);
}
