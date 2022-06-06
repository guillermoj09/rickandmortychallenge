package com.rickandmortychallenge.entity;

import java.util.List;

public class CharacterModel  {
	
	private Long id;
	private String name;
	private String status;
	private String species;
	private String type;
	private List<String> episode;
	private int episode_count;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getEpisode_count() {
		this.episode_count = getEpisode().size();
		return episode_count;
	}

	public void setEpisode_count(int episode_count) {
		
		this.episode_count = episode_count;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public List<String> getEpisode() {
		return episode;
	}
	public void setEpisode(List<String> episode) {
		this.episode = episode;
	}
	

	
}