package io.springbox.recommendations.services;

import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Vinicius Carvalho
 */
@Component
public class RecommendationService {

	@Autowired
	private Recommender userRecommender;

	@Autowired
	private Recommender movieRecommender;

	public List<RecommendedItem> userRecommendations(Long userId){
		try {
			return userRecommender.recommend(userId,10);
		}
		catch (TasteException e) {
			throw new RuntimeException("Could not compute recommendation for user");
		}
	}

	public List<RecommendedItem> movieRecommendations(Long movieId){
		try {
			return movieRecommender.recommend(movieId,10);
		}
		catch (TasteException e) {
			throw new RuntimeException("Could not compute recommendation for movie");
		}
	}



}
