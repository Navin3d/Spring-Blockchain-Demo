package gmc.project.blockchain.models.blockchain;

import java.io.Serializable;

import gmc.project.blockchain.entities.UserEntity;
import lombok.Data;

@Data
public class Docs implements Serializable {
	
	private static final long serialVersionUID = 7993230292031503320L;
	
	private String data;
	
	private UserEntity owner;
	
	private UserEntity client;

}
