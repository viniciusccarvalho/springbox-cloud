package io.springbox.recommendations;

import java.util.List;

import io.springbox.recommendations.services.RecommendationService;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringboxRecommendationsApplication.class)
@WebAppConfiguration
public class SpringboxRecommendationsApplicationTests {

	@Autowired
	private RecommendationService recommendationService;

	@Test
	public void contextLoads() throws Exception{
		List<RecommendedItem> items = recommendationService.userRecommendations(575L);
		List<RecommendedItem> movies = recommendationService.movieRecommendations(296L);
		items.size();
	}

}
