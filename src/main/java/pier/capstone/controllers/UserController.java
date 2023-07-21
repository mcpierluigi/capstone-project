package pier.capstone.controllers;

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

import pier.capstone.entities.User;
import pier.capstone.exceptions.NotFoundException;
import pier.capstone.payloads.UserRegistrationPayload;
import pier.capstone.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public ResponseEntity<Page<User>> getUsers (
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size,
		@RequestParam(defaultValue = "username") String sortBy) {
		Page <User> users = userService.findAllUsers(page, size, sortBy);
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/me")
	public ResponseEntity<User> getCurrentUser(Authentication auth) throws NotFoundException {
		User user = (User) auth.getPrincipal();
		UUID userId = user.getUserId();
		User currentUser = userService.findUserById(userId);
		return ResponseEntity.ok(currentUser);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable UUID id) throws NotFoundException {
		User user = userService.findUserById(id);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody UserRegistrationPayload u) {
		User newUser = userService.createUser(u);
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody UserRegistrationPayload u) throws NotFoundException {
		User updateUser = userService.findUserByIdAndUpdate(id, u);
		return ResponseEntity.ok(updateUser);
		}
}
