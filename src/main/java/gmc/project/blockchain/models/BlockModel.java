package gmc.project.blockchain.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class BlockModel implements Serializable {

	private static final long serialVersionUID = 5365622039811988971L;

	private String id;

	private String data;

	private String hash;

	private String previousHash;

	private Long timeStamp;

	private Integer nonce;

}
