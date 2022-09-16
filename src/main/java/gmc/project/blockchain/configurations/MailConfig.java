package gmc.project.blockchain.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "spring.mail")
public class MailConfig {

	private String host;
	
	private Integer port;
	
	private String username;
	
	private String password;
	
}
