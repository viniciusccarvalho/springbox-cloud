package io.springbox.apigateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


/**
 * @author Vinicius Carvalho
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class SpringboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringboxApplication.class,args);
	}


}
