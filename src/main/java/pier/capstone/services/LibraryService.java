package pier.capstone.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pier.capstone.entities.Library;
import pier.capstone.entities.User;
import pier.capstone.exceptions.NotFoundException;
import pier.capstone.repositories.LibraryRepository;

@Service
public class LibraryService {
	
	@Autowired
	private LibraryRepository libraryRepo;
	
	//CREATE LIBRARY
	public Library createLibrary(User user) {
		Library newLibrary = new Library(user);
		Library savedLibrary = libraryRepo.save(newLibrary);
		user.setLibrary(savedLibrary);
		return savedLibrary;
	}
	
	//FIND BY ID
	public Library findLibraryById(UUID id) throws NotFoundException {
		return libraryRepo.findById(id).orElseThrow(() -> new NotFoundException("Library not found"));
	}
	
	//FIND ALL
	public Page<Library> findAllLibraries(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 20;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return libraryRepo.findAll(pageable);	
	}
	
	//FIND BY USERNAME
	public Library findByUsername(String username) {
		return libraryRepo.findLibraryByUsername(username);
	}
}
