package io.springbox.apigateway;

import java.util.Collections;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringboxApiGatewayApplication.class)
@WebAppConfiguration
public class SpringboxApiGatewayApplicationTests {

	@Autowired
	private RestTemplate template;

	@Test
	public void contextLoads() {

	}

}
