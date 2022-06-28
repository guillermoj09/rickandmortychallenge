package com.rickandmortychallenge.infraestructure.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rickandmortychallenge.domain.model.LocationModel;
import com.rickandmortychallenge.domain.port.output.IGetLocationsGateway;
import com.rickandmortychallenge.infraestructure.gateway.model.ClientLocation;

@Service
public class GetLocationsFromRickAndMortyGateway implements IGetLocationsGateway {
	
	
	private final RestTemplate restTemplate;
	
	@Value("${API_URL_LOCATION}")
	private String API_URL_LOCATION;
	
	public GetLocationsFromRickAndMortyGateway(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public LocationModel execute(int id) {
		
		LocationModel locationModel = getLocationById(id);
		return locationModel;
	}


	private LocationModel getLocationById(int id) {
		ClientLocation clientLocation = getfindByIdLocationFromApi(id);
		LocationModel locationModel = new LocationModel();
		
		locationModel.setDimension(clientLocation.getDimension());
		locationModel.setResidents(clientLocation.getResidents());
		locationModel.setName(clientLocation.getName());
		
		return locationModel;
	}
	
	public ClientLocation getfindByIdLocationFromApi(int id) {
		ClientLocation clienteLocation = null;
		
		ResponseEntity<ClientLocation> response = restTemplate.getForEntity(API_URL_LOCATION+id, ClientLocation.class);
		clienteLocation = response.getBody();
		//LocationModel l = response.getBody();
		return clienteLocation;
	}









}
