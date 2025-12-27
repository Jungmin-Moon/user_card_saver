package com.github.jungmin_moon.yugioh_collection_backend.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Decks")
public class Decks {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String deckName;
	
	@NotNull
	private String deckLocation;
	
	private String username;
	
	@Transient
	private List<Card> mainDeckList;
	
	@Transient
	private List<Card> sideDeckList;

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

	public String getDeckLocation() {
		return deckLocation;
	}

	public void setDeckLocation(String deckLocation) {
		this.deckLocation = deckLocation;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Card> getMainDeckList() {
		return mainDeckList;
	}

	public void setMainDeckList(List<Card> mainDeckList) {
		this.mainDeckList = mainDeckList;
	}

	public List<Card> getSideDeckList() {
		return sideDeckList;
	}

	public void setSideDeckList(List<Card> sideDeckList) {
		this.sideDeckList = sideDeckList;
	}
	
	
}
