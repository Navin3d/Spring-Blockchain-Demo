package gmc.project.blockchain.models.blockchain;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FakeProduct implements Serializable {

	private static final long serialVersionUID = -6102593957680316305L;
	
	private String uid;
	
	private String name;
	
	private String description;
	
	private LocalDateTime dateTime;

}
