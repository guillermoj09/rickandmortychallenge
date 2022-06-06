package com.rickandmortychallenge.rickandmortychallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.rickandmortychallenge.entity.CharacterModel;
import com.rickandmortychallenge.responses.CharacterResponse;
import com.rickandmortychallenge.services.ICharacterService;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class HomeControllerTests {

	@Autowired
	ICharacterService characterService;
	
	private static final String API_URL = "/home";
	
	@Autowired
	TestRestTemplate template;
	
	@Test
	public void getCharacter_retornaOK(){
		ResponseEntity<CharacterResponse> characterResponse = getCharacter(2);
		
		assertEquals(characterResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void getCharacter_sinOrigin(){
		ResponseEntity<CharacterResponse> characterResponse = getCharacter(200);
		
		assertEquals(characterResponse.getBody().getOrigin(), null);
	}
	
	@Test
	public void getCharacter_noExisteIdCharacter(){
		ResponseEntity<CharacterResponse> characterResponse = getCharacter(1000);
		
		assertEquals(characterResponse.getStatusCode(),HttpStatus.NOT_FOUND);
		
	}
	
	
	public <T> ResponseEntity<T> getCharacter(int id){

		ResponseEntity<CharacterResponse> response = template.getForEntity(API_URL+"/"+id, CharacterResponse.class);
		return (ResponseEntity<T>) response;
	}
	
	
	
	
	
}
