package pier.capstone.payloads;

import lombok.Data;

@Data
public class PostPayload {
	private String title, content, postImage, category;
}
