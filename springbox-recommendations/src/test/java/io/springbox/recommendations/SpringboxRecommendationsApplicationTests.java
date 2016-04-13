package io.springbox.recommendations;

import java.util.List;

import io.springbox.recommendations.services.RecommendationService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
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

	@Autowired
	private DataModel dataModel;

	@Test
	public void contextLoads() throws Exception{
		RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
		double score = evaluator.evaluate(new RecommenderBuilder() {
			@Override
			public Recommender buildRecommender(DataModel dataModel) throws TasteException {
				return recommendationService.getMovieRecommender();
			}
		}, null, dataModel, 0.7, 1.0);
		System.out.println(score);
	}

}
