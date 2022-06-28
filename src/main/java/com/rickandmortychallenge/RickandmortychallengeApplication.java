package com.rickandmortychallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.rickandmortychallenge.domain.port.input.IGetCharacterById;
import com.rickandmortychallenge.domain.usecase.GetCharacterByIdUseCase;
import com.rickandmortychallenge.infraestructure.gateway.GetCharactersFromRickAndMortyApiGateway;
import com.rickandmortychallenge.infraestructure.gateway.GetLocationsFromRickAndMortyGateway;

@SpringBootApplication
public class RickandmortychallengeApplication {
	
	@Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(RickandmortychallengeApplication.class, args);
	}
	
	@Bean
	public IGetCharacterById getCharacterById(
			GetCharactersFromRickAndMortyApiGateway characterGateway,
			GetLocationsFromRickAndMortyGateway locationGateway) {
		return new GetCharacterByIdUseCase(characterGateway, locationGateway);
	}
}
