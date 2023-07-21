package pier.capstone.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pier.capstone.entities.Like;

@Repository
public interface LikeRepository extends JpaRepository <Like, UUID> {
	Like findLikeByUserIdAndPostId(UUID userId, UUID postId);
	Like findLikeByUserIdAndLibraryId(UUID userId, UUID libraryId);
}
