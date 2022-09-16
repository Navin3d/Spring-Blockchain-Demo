package gmc.project.blockchain.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginRequestModel implements Serializable {
	
	private static final long serialVersionUID = 5509950249888419004L;
	
	private String userName;
	
	private String password;

}
