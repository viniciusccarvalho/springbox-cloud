package io.springbox.ratings;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Vinicius Carvalho
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringboxRatingsApplication.class)
@WebIntegrationTest({"server.port=0"})
public class SpringboxRatingsApplicationTests {

	@Test
	public void contextLoads() throws Exception{}
}
