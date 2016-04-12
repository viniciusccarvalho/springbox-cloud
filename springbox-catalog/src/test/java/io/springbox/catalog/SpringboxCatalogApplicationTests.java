package io.springbox.catalog;

import io.springbox.catalog.services.DBLoader;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringboxCatalogApplication.class)
@WebAppConfiguration
public class SpringboxCatalogApplicationTests {

	@Autowired
	DBLoader loader;

	@Test
	public void contextLoads() throws Exception{
		loader.load();
	}

}
