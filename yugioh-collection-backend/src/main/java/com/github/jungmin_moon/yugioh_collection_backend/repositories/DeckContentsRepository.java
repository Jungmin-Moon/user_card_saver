package com.github.jungmin_moon.yugioh_collection_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.jungmin_moon.yugioh_collection_backend.entities.DeckContents;

public interface DeckContentsRepository extends JpaRepository<DeckContents, Long>{

}
