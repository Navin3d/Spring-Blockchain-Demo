package gmc.project.blockchain.entities;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
@Entity
@Table(name = "block")
public class BlockEntity implements Serializable {

	private static final long serialVersionUID = 2477201245597637899L;

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String id;

	@Lob
	private String data;

	@Lob
	private String hash;

	@Lob
	private String previousHash;

	private Long timeStamp;

	private Integer nonce;

	@ManyToOne
	private CompanyEntity company;

	public String calculateBlockHash() {
		String dataToHash = previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data;
		MessageDigest digest = null;
		byte[] bytes = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			bytes = digest.digest(dataToHash.getBytes("UTF_8"));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
//            log.log(Level.SEVERE, ex.getMessage());
		}
		StringBuffer buffer = new StringBuffer();
		for (byte b : bytes) {
			buffer.append(String.format("%02x", b));
		}
		return buffer.toString();
	}

	public String mineBlock(int prefix) {
		String prefixString = new String(new char[prefix]).replace('\0', '0');
		while (!hash.substring(0, prefix).equals(prefixString)) {
			nonce++;
			hash = calculateBlockHash();
		}
		return hash;
	}

}
