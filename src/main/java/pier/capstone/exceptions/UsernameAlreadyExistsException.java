package pier.capstone.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
	public UsernameAlreadyExistsException(String message) {
		super(message);
	}
}
