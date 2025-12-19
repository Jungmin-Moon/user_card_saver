package com.github.jungmin_moon.yugioh_collection_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.jungmin_moon.yugioh_collection_backend.entities.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
	
	@Query("SELECT c FROM Card c WHERE c.username = ?1")
	List<Card> countCardsOwned(String username);
	
	
	@Query("SELECT c FROM Card c WHERE c.username = ?1AND c.cardName = ?2")
	Card getCardInfo(String username, String cardName);
	
}
