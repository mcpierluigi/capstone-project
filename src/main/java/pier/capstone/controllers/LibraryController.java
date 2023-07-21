package pier.capstone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pier.capstone.entities.Library;
import pier.capstone.services.LibraryService;

public class LibraryController {
	
	@Autowired 
	private LibraryService libraryService;
	
	@GetMapping
	public ResponseEntity<Page<Library>> getAllLibraries (
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "category") String sortBy) {
		Page<Library> libraries = libraryService.findAllLibraries(page, size, sortBy);
		return ResponseEntity.ok(libraries);
	}
	
	
}
