package com.rickandmortychallenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.rickandmortychallenge.entity.Book;
import com.rickandmortychallenge.entity.CharacterModel;
import com.rickandmortychallenge.entity.LocationModel;
import com.rickandmortychallenge.responses.CharacterResponse;

@Service
public class CharacterServiceImpl implements ICharacterService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired 
	ILocationService locationService;
	
	private static final String API_URL_CHARACTER = "https://rickandmortyapi.com/api/character/";

	@Override
	public CharacterModel findByIdCharacterModel(int id) {
		CharacterModel c = null;
		try {
			ResponseEntity<CharacterModel> response = restTemplate.getForEntity(API_URL_CHARACTER+id, CharacterModel.class);
			c = response.getBody();
		}catch(HttpClientErrorException ex) {
			
		}
		return c;
	}

	@Override
	public CharacterResponse getCharacterResponse(int id) {
		CharacterResponse cResponse = new CharacterResponse();
		CharacterModel characterModel = findByIdCharacterModel(id);
		if(characterModel == null) {
			return null;
		}
		ResponseEntity<LocationModel> location = (ResponseEntity<LocationModel>) this.locationService.findByLocation(id);
		cResponse.setId(characterModel.getId());
		cResponse.setName(characterModel.getName());
		cResponse.setStatus(characterModel.getStatus());
		cResponse.setSpecies(characterModel.getSpecies());
		cResponse.setType(characterModel.getType());
		cResponse.setEpisode_count(characterModel.getEpisode_count());

		
		if(location.getStatusCodeValue() == 200) {
			LocationModel locationM = location.getBody();
			cResponse.setOrigin(locationM);
		}
		
		
		return cResponse;
	}
	

}
