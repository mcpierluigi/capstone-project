package pier.capstone.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pier.capstone.entities.Post;
import pier.capstone.entities.User;
import pier.capstone.exceptions.NotFoundException;
import pier.capstone.exceptions.UnauthorizedException;
import pier.capstone.payloads.PostPayload;
import pier.capstone.payloads.PostWithProductPayload;
import pier.capstone.services.PostService;
import pier.capstone.utils.NerdyCategory;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;
	
	@GetMapping
	public ResponseEntity<Page<Post>> getAllPosts (
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "category") String sortBy) {
		Page<Post> posts = postService.findAllPosts(page, size, sortBy);
		return ResponseEntity.ok(posts);
	}
	
	@PostMapping("")
	public ResponseEntity<Post> createPost(Authentication auth, @RequestBody PostPayload p) 
	throws UnauthorizedException {
		User user = (User) auth.getPrincipal();
		Post newPost = postService.createPost(user, p);
		return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
	}
	
	@PostMapping("")
	public ResponseEntity<Post> createPostWithProduct(Authentication auth, @RequestBody PostWithProductPayload p) 
	throws UnauthorizedException {
		User user = (User) auth.getPrincipal();
		Post newPost = postService.createPostWithProduct(user, p);
		return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
	}
	
	@GetMapping("/search/{filter}/{key}")
	public ResponseEntity<List<Post>> searchPosts(@PathVariable String filter, @PathVariable String key, @PathVariable NerdyCategory category) {
		List<Post> posts;
		if(filter.equalsIgnoreCase("title")) {
			posts = postService.findAllByTitle(key);
		} else if (filter.equalsIgnoreCase("username")) {
			posts = postService.findAllByUsername(key);
		} else if (filter.equalsIgnoreCase("category")) {
			posts = postService.findAllByCategory(category);
		} else {
			posts = new ArrayList<>();
		}
		return ResponseEntity.ok(posts);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> getPostById(@PathVariable UUID id) throws NotFoundException {
		Post post = postService.findPostById(id);
		return ResponseEntity.ok(post);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable UUID id, @RequestBody PostPayload p,
			Authentication auth) throws NotFoundException {
		Post updatedPost = postService.findPostByIdAndUpdate(id, p, auth);
		return ResponseEntity.ok(updatedPost);
	}
}
