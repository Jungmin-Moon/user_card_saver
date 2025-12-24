package com.github.jungmin_moon.yugioh_collection_backend.repository_tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import com.github.jungmin_moon.yugioh_collection_backend.dto.UpdateCardQuantityRequest;
import com.github.jungmin_moon.yugioh_collection_backend.entities.Card;
import com.github.jungmin_moon.yugioh_collection_backend.repositories.CardRepository;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CardRepositoryUnitTests {
	
	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	void givenNewCard_whenSave_thenSuccess() {
		Card card = new Card();
		
		//card.setId(3);
		card.setCardName("Dark Magician");
		card.setCardType("Normal Monster");
		card.setQuantity(5);
		card.setUsername("TestUser3");
		
		Card insertedCard = cardRepository.save(card);
		
		assertThat(entityManager.find(Card.class, insertedCard.getId())).isEqualTo(card);
	}
	
	@Test
	void givenCardCreated_whenFindByName_thenSuccess() {
		Card card = new Card();
		
		card.setCardName("Dark Magician");
		card.setCardType("Normal Monster");
		card.setQuantity(5);
		card.setUsername("TestUser3");
		
		cardRepository.save(card);
		
		Card retrievedCard = cardRepository.getCardInfo("TestUser3", "Dark Magician");
		
		assertThat(retrievedCard).hasFieldOrPropertyWithValue("cardName", retrievedCard.getCardName());
		
	}

	
	@Test
	void givenCardCreated_whenUpdated_thenSuccess() {
		Card card = new Card();
		
		card.setCardName("Dark Magician");
		card.setCardType("Normal Monster");
		card.setQuantity(2);
		card.setUsername("TestUser3");
		
		cardRepository.save(card);
		
		int newQuantity = 5;
		
		UpdateCardQuantityRequest updateCardRequest = new UpdateCardQuantityRequest();
		
		updateCardRequest.setCardName("Dark Magician");
		updateCardRequest.setUpdatedQuanity(newQuantity);
		
		cardRepository.updateCardQuantity("TestUser3", updateCardRequest.getUpdatedQuanity(), updateCardRequest.getCardName());
		
		assertThat(cardRepository.getCardInfo("TestUser3", "Dark Magician").getQuantity() == newQuantity);
	}
}
