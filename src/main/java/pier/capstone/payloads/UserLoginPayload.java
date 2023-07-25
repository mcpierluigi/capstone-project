package pier.capstone.payloads;

import lombok.Data;

@Data
public class UserLoginPayload {
	String email;
	String password;
}
