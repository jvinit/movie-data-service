package com.microservices.moviedataservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservices.moviedataservice.model.Movie;
import com.microservices.moviedataservice.model.MovieSummary;

@RestController
@RequestMapping("/movieApi")
public class MovieController {
	
	@Value("${api.key}")
	private String api_key;
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("movie/{movieId}")
	public Movie getMovieDetails(@PathVariable String movieId) {
		
		String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + api_key;
		MovieSummary movieSummary = restTemplate.getForObject(url, MovieSummary.class);
		
		return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
	}
	
}
