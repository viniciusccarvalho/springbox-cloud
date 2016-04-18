package io.springbox.ratings.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author Vinicius Carvalho
 */
@Entity
@Table(name = "ratings")
@IdClass(Rating.RatingId.class)
public class Rating {


	public Rating(){
		this.timestamp = System.currentTimeMillis();
	}

	public Rating(Integer userId, Integer movieId, Float rating){
		this.userId = userId;
		this.movieId = movieId;
		this.timestamp = System.currentTimeMillis();
		this.rating = rating;
	}



	private Float rating;
	private Long timestamp;
	@Id
	private Integer userId;
	@Id
	private Integer movieId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	@Embeddable
	public static class RatingId implements Serializable{

		Integer userId;
		Integer movieId;

		public RatingId(){}

		public RatingId(Integer userId, Integer movieId) {
			this.userId = userId;
			this.movieId = movieId;
		}

		public Integer getMovieId() {
			return movieId;
		}

		public void setMovieId(Integer movieId) {
			this.movieId = movieId;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}
	}
}
