package com.rickandmortychallenge.services;

import org.springframework.http.ResponseEntity;

import com.rickandmortychallenge.entity.LocationModel;

public interface ILocationService {
	
	public ResponseEntity<?> findByLocation(int id);
	
}
