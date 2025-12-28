package com.github.jungmin_moon.yugioh_collection_backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Decks")
public class Decks {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String deckName;
	
	private String username;
	
	public long getId() {
		
		return id;
		
	}

	public void setId(long id) {
		
		this.id = id;
		
	}

	public String getDeckName() {
		
		return deckName;
		
	}

	public void setDeckName(String deckName) {
		
		this.deckName = deckName;
		
	}

	public String getUsername() {
		
		return username;
		
	}

	public void setUsername(String username) {
		
		this.username = username;
		
	}
	
}
