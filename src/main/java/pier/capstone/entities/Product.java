package pier.capstone.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import pier.capstone.utils.NerdyCategory;

@Entity
@Table(name = "PRODUCT")
@Data
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue
	private UUID productId;
	private String productName, description, linkToBuy, productImage;
	
	@Enumerated(EnumType.STRING)
	private NerdyCategory category;
	
	@ManyToOne
	private Library library;
	
	public Product(String productName, String description, String linkToBuy, NerdyCategory category, String productImage) {
		super();
		this.productName = productName;
		this.description = description;
		this.linkToBuy = linkToBuy;
		this.category = category;
		this.productImage = productImage;
	}
}
