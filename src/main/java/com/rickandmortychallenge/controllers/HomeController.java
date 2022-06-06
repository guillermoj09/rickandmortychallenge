package com.rickandmortychallenge.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rickandmortychallenge.responses.CharacterResponse;
import com.rickandmortychallenge.services.ICharacterService;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	ICharacterService characterService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findCharacter(@PathVariable int id) {
		CharacterResponse characterResponse = null;
		Map<String, Object> response = new HashMap<>();
		characterResponse = characterService.getCharacterResponse(id);
		if( characterResponse == null ) {
			response.put("mensaje", "El cliente ID: ".concat(String.valueOf(id)).concat(" no existe en la api"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CharacterResponse>(characterResponse, HttpStatus.OK);
	}
	@GetMapping()
	public String home() {
		return "Bienvenido";
	}
	
}
