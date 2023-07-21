package pier.capstone.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import pier.capstone.utils.UserRole;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"password"})
public class User {
	@Id
	@GeneratedValue
	private UUID userId;
	private String username, name, lastname, email, password, aboutMe, profileImage;
	private UserRole role;
	
	@OneToOne
	@JoinColumn(name="user_library")
	private Library library;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnoreProperties({"user"})
	private List<Post> posts;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnoreProperties({"user", "likes"})
	private List<Like> likes;

	public User(String username, String name, String lastname, String email, String password, String aboutMe,
			String profileImage) {
		super();
		this.username = username;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.aboutMe = aboutMe;
		this.profileImage = profileImage;
	}
	
	public void addPost(Post post) {
		this.posts.add(post);
		post.setUser(this);
	}
	
	public void removePost(Post post) {
		this.posts.remove(post);
		post.setUser(null);
		post.getLikes().clear();
	}
}
