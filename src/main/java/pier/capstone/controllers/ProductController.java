package pier.capstone.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pier.capstone.entities.Product;
import pier.capstone.entities.User;
import pier.capstone.exceptions.NotFoundException;
import pier.capstone.exceptions.UnauthorizedException;
import pier.capstone.payloads.ProductPayload;
import pier.capstone.services.ProductService;
import pier.capstone.services.UserService;
import pier.capstone.utils.NerdyCategory;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<Page<Product>> getAllProducts (
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "category") String sortBy) {
		Page<Product> products = productService.findAllProducts(page, size, sortBy);
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getPostById(@PathVariable UUID id) throws NotFoundException {
		Product product = productService.findProductById(id);
		return ResponseEntity.ok(product);
	}
	
	@GetMapping("/search/{filter}/{key}")
	public ResponseEntity<List<Product>> searchProducts(@PathVariable String filter, @PathVariable NerdyCategory category) {
		List<Product> products;
		if(filter.equalsIgnoreCase("category")) {
			products = productService.findAllByCategory(category);
		} else {
			products = new ArrayList<>();
		}
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<List<Product>> seartProductsByUsername(@PathVariable String username) {
		List<Product> products;
		User user = userService.findUserByUsername(username);
		products = user.getLibrary();
		return ResponseEntity.ok(products);	
	}
	
	@PostMapping("")
	public ResponseEntity<Product> createProduct(Authentication auth, @RequestBody ProductPayload p) 
	throws UnauthorizedException {
		User user = (User) auth.getPrincipal();
		Product newProduct = productService.createProductForUsersLibrary(user, p);
		return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable UUID id, @RequestBody ProductPayload p,
			Authentication auth) throws NotFoundException {
		Product updatedProduct = productService.findProductByIdAndUpdate(id, p, auth);
		return ResponseEntity.ok(updatedProduct);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) throws NotFoundException {
		productService.findProductByIdAndDelete(id);
		return ResponseEntity.noContent().build();
	}
}
 