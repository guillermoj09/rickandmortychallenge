package com.rickandmortychallenge.domain.port.output;

import com.rickandmortychallenge.domain.model.LocationModel;

public interface IGetLocationsGateway {
	
	public LocationModel execute(int id);

}
