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
@Table(name = "POSTS")
@Data
@NoArgsConstructor
public class Post {
	@Id
	@GeneratedValue
	private UUID postId;
	private Product product;
	private String title, content, postImage;
	
	@Enumerated(EnumType.STRING)
	private NerdyCategory category;
	
	@OneToMany(mappedBy = "article")
	@JsonManagedReference(value = "post_likes")
	private List<Like> likes;
	
	@ManyToOne
	User user;

	public Post(Product product, String title, String content, NerdyCategory category, User user) {
		super();
		this.product = product;
		this.title = title;
		this.content = content;
		this.postImage = product.getProductImage();
		this.category = category;
		this.user = user;
		}

	public Post(String title, String content, NerdyCategory category, User user) {
		super();
		this.title = title;
		this.content = content;
		this.category = category;
		this.user = user;
		}
}
