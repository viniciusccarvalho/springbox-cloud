package io.springbox.ratings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Vinicius Carvalho
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringboxRatingsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringboxRatingsApplication.class,args);
	}
}
