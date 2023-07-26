package pier.capstone.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import pier.capstone.utils.NerdyCategory;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue
	private UUID productId;
	private String productName, brand, description, linkToBuy, productImage;
	
	@Enumerated(EnumType.STRING)
	private NerdyCategory category;
	
	@OneToMany(mappedBy = "product")
	@JsonManagedReference(value = "product_likes")
	private List<Like> likes;
	
	@ManyToOne
	private User user;
	
	public Product(String productName, String brand, String description, String linkToBuy, NerdyCategory category, String productImage) {
		super();
		this.productName = productName;
		this.brand = brand;
		this.description = description;
		this.linkToBuy = linkToBuy;
		this.category = category;
		this.productImage = productImage;
	}
}
