package io.springbox.apigateway;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringboxApiGatewayApplication.class)
@WebIntegrationTest("server.port:0")
public class SpringboxApiGatewayApplicationTests {

	@Autowired
	@Qualifier("loadBalancedRestTemplate")
	@LoadBalanced
	private RestTemplate template;

	@Test
	public void contextLoads() {

		ResponseEntity response = template.exchange("http://springbox-recommendations/recommendations/movies/{mlId}", HttpMethod.GET, null, Map[].class, 1);
		response.getBody();

	}

}
