package gmc.project.blockchain.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserModel implements Serializable {
	
	private static final long serialVersionUID = 2819235043441180401L;
	
	private String id;
	
	private String firstName;

	private String lastName;

	private String userName;

	private String password;

	private String email;

	private String mobileNumber;
	
	private String companyId;

}
