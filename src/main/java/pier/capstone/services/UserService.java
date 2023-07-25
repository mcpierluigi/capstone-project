package pier.capstone.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pier.capstone.entities.User;
import pier.capstone.exceptions.EmailAlreadyExistsException;
import pier.capstone.exceptions.NotFoundException;
import pier.capstone.exceptions.UsernameAlreadyExistsException;
import pier.capstone.payloads.UserRegistrationPayload;
import pier.capstone.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	//CREATE
	public User createUser(UserRegistrationPayload u) {
		 userRepo.findUserByEmail(u.getEmail()).ifPresent(user -> {
			 throw new EmailAlreadyExistsException("Email " + user.getEmail() + " già in uso");
			 });
		 userRepo.findUserByUsername(u.getUsername()).ifPresent(user -> {
			 throw new UsernameAlreadyExistsException("Username " + user.getUsername() + " già in uso");
		 });
		 User newUser = new User(u.getUsername(), u.getName(), u.getLastname(), u.getEmail(), u.getPassword(), u.getAboutMe(),
				 u.getProfileImage());
		 return userRepo.save(newUser);
	}
	
	//FIND ALL
	public Page<User> findAllUsers(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 20;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return userRepo.findAll(pageable);
	};
	
	//FIND BY ID
	public User findUserById(UUID id) throws NotFoundException {
		return userRepo.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));
	}
	
	//FIND BY USERNAME
	public User findUserByUsername(String username) throws NotFoundException {
		return userRepo.findUserByUsername(username).orElseThrow(() -> new NotFoundException("User not found!"));
	}
	 
	//UPDATE USER
	public User findUserByIdAndUpdate(UUID id, UserRegistrationPayload u) throws NotFoundException {
		User foundUser = this.findUserById(id);
		foundUser.setUserId(id);
		foundUser.setUsername(u.getUsername());	
		foundUser.setName(u.getName());
		foundUser.setLastname(u.getLastname());
		foundUser.setEmail(u.getEmail());
		foundUser.setAboutMe(u.getAboutMe());
		foundUser.setProfileImage(u.getProfileImage());
		return userRepo.save(foundUser);
	}
	
	public User findUserByEmail(String email) throws NotFoundException {
		return userRepo.findUserByEmail(email).orElseThrow(() -> new NotFoundException("User not found!"));
	}	
}
