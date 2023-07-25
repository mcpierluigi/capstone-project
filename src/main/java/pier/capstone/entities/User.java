package pier.capstone.entities;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import pier.capstone.utils.UserRole;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"password"})
public class User implements UserDetails {
	@Id
	@GeneratedValue
	private UUID userId;
	private String username, name, lastname, email, password, aboutMe, profileImage;
	private UserRole role;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnoreProperties({"user"})
	private List<Product> library;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnoreProperties({"user"})
	private List<Post> posts;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnoreProperties({"user", "likes"})
	private List<Like> likes;
	
	@OneToMany
	private List<Post> postsAboutProduct;

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
	
	public void addProduct(Product product) {
		this.library.add(product);
		product.setUser(this);
	}
	
	public void removeProduct(Product product) {
		this.library.remove(product);
		product.setUser(null);
		product.getLikes().clear();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
