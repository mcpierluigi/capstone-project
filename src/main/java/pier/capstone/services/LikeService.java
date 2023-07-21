package pier.capstone.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pier.capstone.entities.Library;
import pier.capstone.entities.Like;
import pier.capstone.entities.Post;
import pier.capstone.entities.User;
import pier.capstone.exceptions.NotFoundException;
import pier.capstone.repositories.LibraryRepository;
import pier.capstone.repositories.LikeRepository;
import pier.capstone.repositories.PostRepository;

@Service
public class LikeService {

	@Autowired
	private LikeRepository likeRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private LibraryRepository libraryRepo;
	
	//CREATE LIKE FOR POST 
	public Like createLikeForPost(Like like, UUID userId, UUID postId) throws NotFoundException {
		User user = userService.findUserById(userId);
		Post post = postRepo.findById(postId).orElse(null);
		
		if (post != null) {
			Like likeAlreadyExistsForPost = likeRepo.findLikeByUserIdAndPostId(userId, postId);
			if (likeAlreadyExistsForPost != null) {
				deleteLikeFromPost(userId, postId);
				return null;
			} 
			like.setPost(post);	
			like.setUser(user);
			return likeRepo.save(like);
		}
		return null;
	}
	
	//CREATE LIKE FOR LIBRARY
	public Like createLikeForLibrary(Like like, UUID userId, UUID libraryId) throws NotFoundException {
		User user = userService.findUserById(userId);
		Library library = libraryRepo.findById(libraryId).orElse(null);
		
		if(library != null) {
			Like likeAlreadyExistsForLibrary = likeRepo.findLikeByUserIdAndLibraryId(userId, libraryId);
			if (likeAlreadyExistsForLibrary != null) {
				deleteLikeFromLibrary(userId, libraryId);
				return null;
			}
			like.setLibrary(library);	
			like.setUser(user);			
			return likeRepo.save(like);
		}
		return null;
	}
	
	//DELETE LIKE FOR POST
	public void deleteLikeFromPost(UUID userId, UUID postId) {
		Like likeForPost = likeRepo.findLikeByUserIdAndPostId(userId, postId);
		if(likeForPost != null) {
			likeForPost.getPost().getLikes().remove(likeForPost);
		}
	}
	
	//DELETE LIKE FOR LIBRARY
	public void deleteLikeFromLibrary(UUID userId, UUID libraryId) {
		Like likeForLibrary = likeRepo.findLikeByUserIdAndLibraryId(userId, libraryId);
		if(likeForLibrary != null) {
			likeForLibrary.getPost().getLikes().remove(likeForLibrary);
		}
	}
}
