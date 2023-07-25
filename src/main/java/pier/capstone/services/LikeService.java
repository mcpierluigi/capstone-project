package pier.capstone.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pier.capstone.entities.Like;
import pier.capstone.entities.Post;
import pier.capstone.entities.Product;
import pier.capstone.entities.User;
import pier.capstone.exceptions.NotFoundException;
import pier.capstone.repositories.LikeRepository;
import pier.capstone.repositories.PostRepository;
import pier.capstone.repositories.ProductRepository;

@Service
public class LikeService {

	@Autowired
	private LikeRepository likeRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	//CREATE LIKE FOR POST 
	public Like createLikeForPost(Like like, UUID userId, UUID postId) throws NotFoundException {
		User user = userService.findUserById(userId);
		Post post = postRepo.findById(postId).orElse(null);
		
		if (post != null) {
			Like likeAlreadyExistsForPost = likeRepo.findLikeByUserUserIdAndPostPostId(userId, postId);
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
	public Like createLikeForProduct(Like like, UUID userId, UUID productId) throws NotFoundException {
		User user = userService.findUserById(userId);
		Product product = productRepo.findById(productId).orElse(null);
		
		if(product != null) {
			Like likeAlreadyExistsForProduct = likeRepo.findLikeByUserUserIdAndProductProductId(userId, productId);
			if (likeAlreadyExistsForProduct != null) {
				deleteLikeFromProduct(userId, productId);
				return null;
			}
			like.setProduct(product);	
			like.setUser(user);			
			return likeRepo.save(like);
		}
		return null;
	}
	
	//DELETE LIKE FOR POST
	public void deleteLikeFromPost(UUID userId, UUID postId) {
		Like likeForPost = likeRepo.findLikeByUserUserIdAndPostPostId(userId, postId);
		if(likeForPost != null) {
			likeForPost.getPost().getLikes().remove(likeForPost);
		}
	}
	
	//DELETE LIKE FOR LIBRARY
	public void deleteLikeFromProduct(UUID userId, UUID productId) {
		Like likeForProduct = likeRepo.findLikeByUserUserIdAndProductProductId(userId, productId);
		if(likeForProduct != null) {
			likeForProduct.getProduct().getLikes().remove(likeForProduct);
		}
	}
}
