package pier.capstone.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import pier.capstone.entities.*;
import pier.capstone.exceptions.NotFoundException;
import pier.capstone.exceptions.UnauthorizedException;
import pier.capstone.payloads.PostPayload;
import pier.capstone.payloads.PostWithProductPayload;
import pier.capstone.repositories.PostRepository;
import pier.capstone.utils.NerdyCategory;
import pier.capstone.utils.UserRole;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepo;
	
	//CREATE POST WITH PRODUCT
	public Post createPostWithProduct(User user, PostWithProductPayload p) {
		Post newPost = new Post(p.getProduct(), p.getTitle(), p.getContent(), p.getCategory(), user);
		Post savedPost = postRepo.save(newPost);
		user.addPost(savedPost);
		return savedPost;
	}
	
	//CREARE POST WITHOUT PRODUCT
	public Post createPost (User user, PostPayload p) {
		Post newPost = new Post(p.getTitle(), p.getContent(), p.getCategory(), user);
		Post savedPost = postRepo.save(newPost);
		user.addPost(savedPost);
		return savedPost;
	}
	
	//FIND BY ID
	public Post findPostById(UUID id) throws NotFoundException {
		return postRepo.findById(id).orElseThrow(() -> new NotFoundException("Post not found"));
	}
	
	//FIND ALL
	public Page<Post> findAllPosts(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 20;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return postRepo.findAll(pageable);
	}
	
	//FIND ALL BY CATEGORY
	public List<Post> findAllByCategory(NerdyCategory categroy) {
		return postRepo.findAllPostsByCategory(categroy);
	}
	
	//FIND ALL BY USERNAME
	public List<Post> findAllByUsername(String username) {
		return postRepo.findAllPostsByUsername(username);	
	}
	
	//FIND ALL BY TITLE
	public List<Post> findAllByTitle(String title) {
		return postRepo.findAllPostsByUsername(title);
	}
	
	//UPDATE POST
	public Post findPostByIdAndUpdate(UUID id, PostPayload p, Authentication authentication) {
		Post foundPost = this.findPostById(id);
		User authenticatedUser = (User) authentication.getPrincipal();
		//AUTH
		if(!foundPost.getUser().getUserId().equals(authenticatedUser.getUserId())
			&& !authenticatedUser.getRole().equals(UserRole.USER)) {
				throw new UnauthorizedException("Unauthorized user!");
			}
		foundPost.setPostId(id);
		foundPost.setTitle(p.getTitle());
		foundPost.setContent(p.getContent());
		foundPost.setCategory(p.getCategory());
		return postRepo.save(foundPost);
		}
	
	public Post findPostWithProductByIdAndUpdate(UUID id, PostWithProductPayload p, Authentication authentication) {
		Post foundPost = this.findPostById(id);
		User authenticatedUser = (User) authentication.getPrincipal();
		//AUTH
		if(!foundPost.getUser().getUserId().equals(authenticatedUser.getUserId())
			&& !authenticatedUser.getRole().equals(UserRole.USER)) {
				throw new UnauthorizedException("Unauthorized user!");
			}
		foundPost.setPostId(id);
		foundPost.setProduct(p.getProduct());
		foundPost.setTitle(p.getTitle());
		foundPost.setContent(p.getContent());
		foundPost.setCategory(p.getCategory());
		return postRepo.save(foundPost);
		}
}
