package pier.capstone.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LIBRARY")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"user"})
public class Library {
	@Id
	@GeneratedValue
	private UUID libraryId;
	
	@OneToOne
	@JoinColumn(name="user_id")
	User user;
	
	@OneToMany(mappedBy="library")
	@JsonManagedReference(value = "library_likes")
	private List<Like> likes;
	 
	@OneToMany(mappedBy = "library")
	private List<Product> products;
	
	public void addProduct(Product product) {
		this.products.add(product);
		product.setLibrary(this);
	}
	
	public void removeProdcut(Product product) {
		this.products.remove(product);
		product.setLibrary(null);
	}

	public Library(User user) {
		super();
		this.user = user;
	}
}
