package pier.capstone.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pier.capstone.entities.Product;
import pier.capstone.utils.NerdyCategory;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
	List<Product> findAllProductsByCategory(NerdyCategory category);
}
