package pier.capstone.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LIKES")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"user"})
public class Like {
	@Id
	@GeneratedValue
	private UUID likeId;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	@JsonBackReference(value = "post_likes")
	private Post post;
	
	@ManyToOne 
	@JsonBackReference(value = "library_likes")
	private Library library;
}
