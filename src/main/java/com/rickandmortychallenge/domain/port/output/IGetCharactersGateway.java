package com.rickandmortychallenge.domain.port.output;

import com.rickandmortychallenge.domain.model.CharacterModel;

public interface IGetCharactersGateway {
	public CharacterModel execute(int id);
}
