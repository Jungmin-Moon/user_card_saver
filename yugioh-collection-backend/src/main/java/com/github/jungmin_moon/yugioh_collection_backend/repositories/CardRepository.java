package com.github.jungmin_moon.yugioh_collection_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.jungmin_moon.yugioh_collection_backend.entities.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
	
	@Query("SELECT c FROM Card c WHERE c.username = :username")
	List<Card> countCardsOwned(@Param("username") String username);
	
	
	@Query("SELECT c FROM Card c WHERE c.username = :username AND c.cardName = :cardName")
	Card getCardInfo(@Param("username") String username, @Param("cardName") String cardName);
	
	
	@Query("SELECT c FROM Card c WHERE c.username = :username AND c.quantity = :quantity")
	List<Card> getCardsByQuantity(@Param("username") String username, @Param("quantity")int quantity);
	
	@Modifying
	@Query("UPDATE Card c SET c.quantity = :quantity WHERE c.username = :username AND c.cardName = :cardName")
	void updateCardQuantity(@Param("username") String username, @Param("quantity") int quantity, @Param("cardName") String cardName);
	
	@Modifying
	@Query("UPDATE Card c SET c.cardName = :newCardName WHERE c.username = :username AND c.cardName = :oldCardName")
	void updateCardName(@Param("username") String username, @Param("oldCardName") String oldCardName, @Param("newCardName") String newCardName);
	
	@Modifying
	@Query("UPDATE Card c SET c.cardType = :newCardType WHERE c.username = :username AND c.cardName = :cardName")
	void updateCardType(@Param("username") String username, @Param("cardName") String cardName, @Param("newCardType") String newCardType);
	
	@Query("SELECT c FROM Card c WHERE c.username = :username AND c.cardName LIKE %:wordToFind%")
	List<Card> getCardsWithWordInName(@Param("username") String username, @Param("wordToFind") String wordToFind);
}
