package pier.capstone.payloads;

import lombok.Data;
import pier.capstone.utils.NerdyCategory;

@Data
public class ProductPayload {
	private String productName, description, linkToBuy, prodcutImage;
	private NerdyCategory category;
}