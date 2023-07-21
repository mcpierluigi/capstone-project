package pier.capstone.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationPayload {
	
	@Size(min = 8, max = 15, message = "Username must be at most 15 characters long")
	private String username;
	
	@Size(min = 3, max = 20, message = "Name must be at most 20 characters long")
	private String name;
	
	@Size(min = 3, max = 20, message = "Surname must be at most 20 characters long")
	private String lastname;
	
	@Email(message = "Invalid email format")
	private String email;
	private String password;
	private String aboutMe;
	private String profileImage;
}
