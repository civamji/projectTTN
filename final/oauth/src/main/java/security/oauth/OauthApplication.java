package security.oauth;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OauthApplication {

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(OauthApplication.class, args);

	}
}
