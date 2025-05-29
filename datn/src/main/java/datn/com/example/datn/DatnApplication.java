package datn.com.example.datn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SecurityProperties.class)
public class DatnApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatnApplication.class, args);
	}

}
