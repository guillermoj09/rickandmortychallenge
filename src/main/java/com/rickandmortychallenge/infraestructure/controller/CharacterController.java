package com.rickandmortychallenge.infraestructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rickandmortychallenge.domain.model.CharacterModel;
import com.rickandmortychallenge.domain.port.input.IGetCharacterById;

@RestController
@RequestMapping("/character")
public class CharacterController {
	
	@Autowired
	private IGetCharacterById getCharacterById;
	
	
	
	@GetMapping("/{id}")
	public CharacterModel findCharacter(@PathVariable int id) {
		CharacterModel characterModel = getCharacterById.execute(id);
		return characterModel;
	
	}
	
	@GetMapping("/home")
	public String Home() {
		//CharacterModel characterModel = getCharacterById.execute(id);
		return "Bienvenido";
	
	}
	
}
