package com.rickandmortychallenge.infraestructure.gateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rickandmortychallenge.domain.model.CharacterModel;
import com.rickandmortychallenge.domain.port.output.IGetCharactersGateway;
import com.rickandmortychallenge.infraestructure.gateway.model.ClientCharacter;

@Service
public class GetCharactersFromRickAndMortyApiGateway implements IGetCharactersGateway {
	
	
	private final RestTemplate restTemplate;
	
	@Value("${API_URL_CHARACTER}")
	private String API_URL_CHARACTER;
	
	public GetCharactersFromRickAndMortyApiGateway(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public CharacterModel execute(int id) {
		CharacterModel characterModel = getCharacterById(id);
		return characterModel;
	}
	
	private ClientCharacter getfindByIdCharactersFromApi(int id){
		ClientCharacter clientCharacter = null;
		ResponseEntity<ClientCharacter> response = restTemplate.getForEntity(API_URL_CHARACTER+id, ClientCharacter.class);
		clientCharacter = response.getBody();
		return clientCharacter;
	}
	
	private CharacterModel getCharacterById(int id) {
		CharacterModel characterModel = new CharacterModel();
		ClientCharacter clientCharacter = getfindByIdCharactersFromApi(id);
		
		characterModel.setId(clientCharacter.getId());
		characterModel.setName(clientCharacter.getName());
		characterModel.setStatus(clientCharacter.getStatus());
		characterModel.setSpecies(clientCharacter.getSpecies());
		characterModel.setType(clientCharacter.getType());
		//characterModel.setEpisode_count(clientCharacter.getEpisode());
		
		
		return characterModel;
	}
	
}
