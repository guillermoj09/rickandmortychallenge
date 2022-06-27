package com.rickandmortychallenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.rickandmortychallenge.entity.CharacterModel;
import com.rickandmortychallenge.entity.LocationModel;

@Service
public class LocactionServiceImpl implements ILocationService{
	
	@Value("${API_URL_LOCATION}")
	private String API_URL_LOCATION;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public ResponseEntity<?> findByLocation(int id) {
		ResponseEntity<LocationModel> response = null;
		try {
			response = restTemplate.getForEntity(API_URL_LOCATION+id, LocationModel.class);
		}catch(HttpClientErrorException ex) {
			return new ResponseEntity<>(ex.getResponseBodyAsString(), ex.getStatusCode());
		}
		//LocationModel l = response.getBody();
		return response;
	}

}
