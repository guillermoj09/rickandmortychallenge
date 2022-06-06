package com.rickandmortychallenge.services;

import java.util.List;

import com.rickandmortychallenge.entity.CharacterModel;
import com.rickandmortychallenge.responses.CharacterResponse;

public interface ICharacterService {

	public CharacterModel findByIdCharacterModel(int id);
	
	public CharacterResponse getCharacterResponse(int id);
}
