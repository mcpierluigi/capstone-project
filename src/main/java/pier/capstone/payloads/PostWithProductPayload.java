package pier.capstone.payloads;

import lombok.Data;
import pier.capstone.entities.Product;
import pier.capstone.utils.NerdyCategory;

@Data
public class PostWithProductPayload {
	private Product product;
	private String title, content;
	private NerdyCategory category;
}
