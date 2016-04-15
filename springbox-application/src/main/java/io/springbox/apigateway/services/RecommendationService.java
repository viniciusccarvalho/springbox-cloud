package io.springbox.apigateway.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.netflix.discovery.converters.Auto;
import io.springbox.apigateway.domain.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @author Vinicius Carvalho
 */
@Service
public class RecommendationService {

	private RestTemplate client;

	@Autowired
	public RecommendationService(RestTemplate client) {
		this.client = client;
	}

	public List<Movie> recommendations(Integer movieId){
		List<JsonNode> similarItems = client.exchange("http://SPRINGBOX-RECOMMENDATIONS/movies/{movieId}",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<JsonNode>>(){},
				movieId).getBody();
		List<Integer> ids = similarItems.stream().map(jsonNode -> {return jsonNode.get("itemID").asInt();}).collect(Collectors.toList());
		String params = StringUtils.collectionToCommaDelimitedString(ids);
		List<Movie> similarMovies = client.exchange("http://SPRINGBOX-CATALOG/movies/{params}",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Movie>>() {},
				params).getBody();
		return similarMovies;
	}

}
