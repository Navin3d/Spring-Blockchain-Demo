package gmc.project.blockchain.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "auth")
public class AuthConfig {

	private String authUrl;

	private String jwtSecret;

	private String issuer;

	private Long expeiry;

	private Long refreshToken;

}
