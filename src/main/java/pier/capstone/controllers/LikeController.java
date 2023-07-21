package pier.capstone.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pier.capstone.entities.Like;
import pier.capstone.exceptions.NotFoundException;
import pier.capstone.services.LikeService;

@RestController
@RequestMapping("/likes")
public class LikeController {
	
	@Autowired
	private LikeService likeService;
	
	@PostMapping("/{userId}/{postId}")
	public Like createLikePost(@RequestBody Like like, @PathVariable("userId") UUID userId,  @PathVariable("postId") UUID postId) throws NotFoundException {
		return likeService.createLikeForPost(like, userId, postId);
	}
	
	@PostMapping("/{userId}/{libraryId}")
	public Like createLikeLibrary(@RequestBody Like like, @PathVariable("userId") UUID userId, @PathVariable("libraryId") UUID libraryId) throws NotFoundException {
		return likeService.createLikeForLibrary(like, userId, libraryId);
	}
	
	@DeleteMapping("/{userId}/{postId}")
	public void deleteLikePost(@PathVariable("userId") UUID userId, @PathVariable("postId") UUID postId) throws NotFoundException {
		likeService.deleteLikeFromPost(userId, postId);
	}
	
	@DeleteMapping("/{userId}/{libraryId}")
	public void deleteLikeLibrary(@PathVariable("userId") UUID userId, @PathVariable("libraryId") UUID libraryId) throws NotFoundException {
		likeService.deleteLikeFromLibrary(userId, libraryId);
	}
}
