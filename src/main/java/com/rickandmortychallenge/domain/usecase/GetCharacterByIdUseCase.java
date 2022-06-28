package com.rickandmortychallenge.domain.usecase;

import com.rickandmortychallenge.domain.model.CharacterModel;
import com.rickandmortychallenge.domain.model.LocationModel;
import com.rickandmortychallenge.domain.model.OriginModel;
import com.rickandmortychallenge.domain.port.input.IGetCharacterById;
import com.rickandmortychallenge.domain.port.output.IGetCharactersGateway;
import com.rickandmortychallenge.domain.port.output.IGetLocationsGateway;

public class GetCharacterByIdUseCase implements IGetCharacterById {
	
	private final IGetCharactersGateway getCharactersGateway;
	private final IGetLocationsGateway getLocationGateway;
	
	public GetCharacterByIdUseCase(IGetCharactersGateway getCharactersGateway,IGetLocationsGateway getLocationGateway) {
		this.getCharactersGateway = getCharactersGateway;
		this.getLocationGateway = getLocationGateway;
	}
	
	@Override
	public CharacterModel execute(int id) {
		CharacterModel charactersWithoutLocation = getCharactersGateway.execute(id);
		
		LocationModel locationModel = getLocationGateway.execute(id);
		
		OriginModel originModel = new OriginModel();
		originModel.setResidents(locationModel.getResidents());
		originModel.setDimension(locationModel.getDimension());
		originModel.setUrl(locationModel.getUrl());
		originModel.setName(locationModel.getName());
		charactersWithoutLocation.setOriginModel(originModel);
		
		
		return charactersWithoutLocation;
	}

}
