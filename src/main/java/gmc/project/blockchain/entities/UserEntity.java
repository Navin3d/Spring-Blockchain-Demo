package gmc.project.blockchain.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(exclude = {"ownerOfCompany"})
@Table(name = "users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 2817611632159200174L;

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	private String id;

	private String firstName;

	private String lastName;

	private String userName;

	private String password;

	private String email;

	private String mobileNumber;
	
	@OneToOne(mappedBy = "companyOwner")
	private CompanyEntity ownerOfCompany;

}
