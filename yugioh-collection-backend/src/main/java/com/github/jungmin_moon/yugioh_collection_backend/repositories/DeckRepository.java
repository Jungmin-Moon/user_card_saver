package com.github.jungmin_moon.yugioh_collection_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.jungmin_moon.yugioh_collection_backend.entities.Decks;

public interface DeckRepository extends JpaRepository<Decks, Long>{
	
	

}
