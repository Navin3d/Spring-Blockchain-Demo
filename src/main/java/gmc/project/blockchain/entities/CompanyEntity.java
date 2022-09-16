package gmc.project.blockchain.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(exclude = {"blockChain"})
@Table(name = "companies")
public class CompanyEntity implements Serializable {

	private static final long serialVersionUID = 964089499562978424L;

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String id;
	
	private String companyName;
	
	private String gstNumber;
	
	@Lob
	private String publicKey;
	
	@OneToOne
	private UserEntity companyOwner; 
	
	@OneToMany(mappedBy = "company")
	private Set<BlockEntity> blockChain = new HashSet<>();
	
}
