package com.rickandmortychallenge.domain.port.input;



import com.rickandmortychallenge.domain.model.CharacterModel;


public interface IGetCharacterById {
	public CharacterModel execute(int id);
}
