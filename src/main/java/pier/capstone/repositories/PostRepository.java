package pier.capstone.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pier.capstone.entities.Post;


@Repository
public interface PostRepository extends JpaRepository<Post, UUID>{
	List<Post> findAllPostsByCategory(String category);
	List<Post> findAllPostsByUserUsername(String username);
	List<Post> findAllPostsByTitle(String title);
}
