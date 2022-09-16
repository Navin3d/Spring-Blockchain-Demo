package gmc.project.blockchain.models.blockchain;

import java.io.Serializable;

import lombok.Data;

@Data
public class FakeProductMiningModel implements Serializable {
	
	private static final long serialVersionUID = 7769618593857669690L;
	
	private String id;
	
	private String companyId;
	
	private FakeProduct fakeProduct;
	

}
