package com.github.jungmin_moon.yugioh_collection_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.jungmin_moon.yugioh_collection_backend.entities.Decks;

public interface DeckRepository extends JpaRepository<Decks, Long>{
	
	@Query("SELECT d from Decks d WHERE d.username = :username")
	List<Decks> getAllByUsername(@Param("username")String username);

}
