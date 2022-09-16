package gmc.project.blockchain.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "engineSettings")
public class EngineSettingsEntity implements Serializable {

	private static final long serialVersionUID = 8969936773886919965L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	private String name;

	private String description;

	private String strField1;

	private String strField2;

	private String strField3;

	private Integer intField1;

	private Integer intField2;

	private Integer intField3;

}
