package pier.capstone.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
public class Post {
	@Id
	@GeneratedValue
	private UUID postId;

	private String title, content, postImage, category;
	
	@OneToMany(mappedBy = "post")
	@JsonManagedReference(value = "post_likes")
	private List<Like> likes;
		
	@ManyToOne
	User user;

	public Post(String title, String content, String postImage, String category, User user, List<Like> likes) {
		super();
		this.title = title;
		this.content = content;
		this.category = category;
		this.user = user;
		this.likes = likes;
		}
}
