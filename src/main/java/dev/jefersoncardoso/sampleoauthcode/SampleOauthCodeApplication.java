package dev.jefersoncardoso.sampleoauthcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SampleOauthCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleOauthCodeApplication.class, args);
	}

}
