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

import pier.capstone.entities.Product;
import pier.capstone.entities.User;
import pier.capstone.exceptions.NotFoundException;
import pier.capstone.exceptions.UnauthorizedException;
import pier.capstone.payloads.ProductPayload;
import pier.capstone.repositories.ProductRepository;
import pier.capstone.utils.NerdyCategory;
import pier.capstone.utils.UserRole;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepo;
	
	//CREATE
	public Product createProductForUsersLibrary(User user, ProductPayload p) 
	throws NotFoundException {
		Product newProduct = new Product(p.getProductName(), p.getDescription(), p.getLinkToBuy(), p.getCategory(), p.getProdcutImage());
		Product savedProduct = productRepo.save(newProduct);
		user.addProduct(savedProduct);
			return productRepo.save(newProduct);
	}
	
	//FIND BY ID
	public Product findProductById(UUID id) throws NotFoundException {
		return productRepo.findById(id).orElseThrow(() -> new NotFoundException("Product not found!"));
	}
	
	//FIND ALL
	public Page<Product> findAllProducts(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 20;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return productRepo.findAll(pageable);
	};
	
	//FIND BY CATEGORY
	public List<Product> findAllByCategory(NerdyCategory category) {
		return productRepo.findAllProductsByCategory(category);
	}
	
	//UPDATE PRODUCT
	public Product findProductByIdAndUpdate(UUID id, ProductPayload p, Authentication authentication) {
		Product foundProduct = this.findProductById(id);
		User authenticatedUser = (User) authentication.getPrincipal();
		if(!foundProduct.getUser().getUserId().equals(authenticatedUser.getUserId())
				&& !authenticatedUser.getRole().equals(UserRole.USER)) {
			throw new UnauthorizedException("Unauthorized user!");
			}
		foundProduct.setProductId(id);
		foundProduct.setProductName(p.getProductName());
		foundProduct.setDescription(p.getDescription());
		foundProduct.setLinkToBuy(p.getLinkToBuy());
		return productRepo.save(foundProduct);
	}
}
