package gmc.project.blockchain.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CompanyModel implements Serializable {

	private static final long serialVersionUID = 56191992801377241L;
	
	private String id;

	private String companyName;

	private String gstNumber;
	
	private String publicKey;

	private UserModel companyOwner;
	
	private List<BlockModel> blockChain = new ArrayList<>();

}
