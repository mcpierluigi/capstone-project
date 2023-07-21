package pier.capstone.payloads;

import lombok.Data;
import pier.capstone.utils.NerdyCategory;

@Data
public class PostPayload {
	private String title, content;
	private NerdyCategory category;
}
