package com.github.jungmin_moon.yugioh_collection_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.jungmin_moon.yugioh_collection_backend.entities.DeckContents;

public interface DeckContentsRepository extends JpaRepository<DeckContents, Long>{
	
	@Query()
	List<DeckContents> getAllByDeckName(@Param("deckName")String deckName, @Param("username")String username);
}
