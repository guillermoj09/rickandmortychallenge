package com.rickandmortychallenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.rickandmortychallenge.entity.CharacterModel;
import com.rickandmortychallenge.entity.LocationModel;
import com.rickandmortychallenge.responses.CharacterResponse;

@Service
public class CharacterServiceImpl implements ICharacterService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired 
	private ILocationService locationService;
	
	@Value("${API_URL_CHARACTER}")
	private String API_URL_CHARACTER;

	@Override
	public CharacterModel findByIdCharacterModel(int id) {
		CharacterModel characterModel = null;
		ResponseEntity<CharacterModel> response = restTemplate.getForEntity(API_URL_CHARACTER+id, CharacterModel.class);
		characterModel = response.getBody();
		return characterModel;
	}

	@Override
	public CharacterResponse getCharacterResponse(int id) {
		CharacterResponse characterResponse = new CharacterResponse();
		CharacterModel characterModel = findByIdCharacterModel(id);
		if(characterModel == null) {
			return null;
		}
		characterResponse.setId(characterModel.getId());
		characterResponse.setName(characterModel.getName());
		characterResponse.setStatus(characterModel.getStatus());
		characterResponse.setSpecies(characterModel.getSpecies());
		characterResponse.setType(characterModel.getType());
		characterResponse.setEpisode_count(characterModel.getEpisode_count());
		ResponseEntity<LocationModel> location = (ResponseEntity<LocationModel>) this.locationService.findByLocation(id);

		if(location.getStatusCodeValue() == 200) {
			LocationModel locationM = location.getBody();
			characterResponse.setOrigin(locationM);
		}
		
		return characterResponse;
	}
	
}
