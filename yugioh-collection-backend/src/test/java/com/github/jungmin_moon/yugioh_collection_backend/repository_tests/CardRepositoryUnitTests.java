package com.github.jungmin_moon.yugioh_collection_backend.repository_tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import com.github.jungmin_moon.yugioh_collection_backend.dto.UpdateCardNameRequest;
import com.github.jungmin_moon.yugioh_collection_backend.dto.UpdateCardQuantityRequest;
import com.github.jungmin_moon.yugioh_collection_backend.dto.UpdateCardTypeRequest;
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
	void givenCardCreated_whenUpdatedQuantity_thenSuccess() {
		Card card = new Card();
		
		card.setCardName("Dark Magician");
		card.setCardType("Normal Monster");
		card.setQuantity(2);
		card.setUsername("TestUser3");
		
		cardRepository.save(card);
		
		int newQuantity = 5;
		
		UpdateCardQuantityRequest updateCardRequest = new UpdateCardQuantityRequest("Dark Magician", newQuantity);
		
		cardRepository.updateCardQuantity("TestUser3", updateCardRequest.updatedQuantity(), updateCardRequest.cardName());
		
		assertThat(cardRepository.getCardInfo("TestUser3", "Dark Magician").getQuantity() == newQuantity);
	}
	
	@Test
	void givenCardCreated_whenUpdatedName_thenSuccess() {
		Card card = new Card();
		
		card.setCardName("Dark Magician");
		card.setCardType("Normal Monster");
		card.setQuantity(2);
		card.setUsername("TestUser3");
		
		cardRepository.save(card);
		
		UpdateCardNameRequest updateCardNameRequest = new UpdateCardNameRequest("Dark Magician", "Dark Magician of Chaos");
		
		long id = cardRepository.getCardInfo("TestUser3", "Dark Magician").getId();
		
		cardRepository.updateCardName("TestUser3", updateCardNameRequest.oldCardName(), updateCardNameRequest.newCardName());
		
		assertThat(cardRepository.getReferenceById(id).getCardName().equals(updateCardNameRequest.newCardName()));
	}
	
	
	@Test
	void givenCardCreated_whenUpdatedCardType_thenSuccess() {
		Card card = new Card();
		
		card.setCardName("Dark Magician");
		card.setCardType("Normal Monster");
		card.setQuantity(2);
		card.setUsername("TestUser3");
		
		cardRepository.save(card);
		
		UpdateCardTypeRequest updateCardTypeRequest = new UpdateCardTypeRequest("Dark Magician", "Effect Monster");
		
		cardRepository.updateCardType("TestUser3", updateCardTypeRequest.cardName(), updateCardTypeRequest.newCardType());
		
		assertThat(cardRepository.getCardInfo("TestUser3", card.getCardName()).getCardType().equals(updateCardTypeRequest.newCardType()));
	} 
	
	@Test
	void givenCardsCreated_whenCardsCountSame_thenSuccess() {
		Card card1 = new Card();
		Card card2 = new Card();
		
		card1.setCardName("Dark Magician");
		card2.setCardName("Blue-Eyes White Dragon");
		
		card1.setCardType("Normal Monster");
		card2.setCardType("Normal Monster");
		
		card1.setQuantity(4);
		card2.setQuantity(6);
		
		card1.setUsername("TestUser3");
		card2.setUsername("TestUser3");
		
		cardRepository.save(card1);
		cardRepository.save(card2);
		
		int cardCollectionSize = cardRepository.getAllOwnedByUsername("TestUser3").size();
		
		assertThat(cardCollectionSize == 2);
		
	}
	
	@Test
	void givenCardsCreated_findWithSubString_thenSuccess() {
		Card card1 = new Card();
		Card card2 = new Card();
		
		card1.setCardName("Dark Magician");
		card2.setCardName("Blue-Eyes White Dragon");
		
		card1.setCardType("Normal Monster");
		card2.setCardType("Normal Monster");
		
		card1.setQuantity(4);
		card2.setQuantity(6);
		
		card1.setUsername("TestUser3");
		card2.setUsername("TestUser3");
		
		cardRepository.save(card1);
		cardRepository.save(card2);
		
		var cardsWithSubStringInName = cardRepository.getCardsWithWordInName("TestUser3", "Mag");
		
		assertThat(cardsWithSubStringInName.size() == 1);
	}
	
	@Test
	void givenCardsCreated_findWithGivenQuantity_thenSuccess() {
		Card card1 = new Card();
		Card card2 = new Card();
		Card card3 = new Card();
		
		card1.setCardName("Dark Magician");
		card2.setCardName("Blue-Eyes White Dragon");
		card3.setCardName("Red-Eyes Black Dragon");
		
		card1.setCardType("Normal Monster");
		card2.setCardType("Normal Monster");
		card3.setCardType("Normal Monster");
		
		card1.setQuantity(4);
		card2.setQuantity(6);
		card3.setQuantity(4);
		
		card1.setUsername("TestUser3");
		card2.setUsername("TestUser3");
		card3.setUsername("TestUser3");
		
		cardRepository.save(card1);
		cardRepository.save(card2);
		cardRepository.save(card3);
		
		var cardsWithGivenQuantity = cardRepository.getCardsByQuantity("TestUser3", 4);
		
		assertThat(cardsWithGivenQuantity.size() == 2);
	}
}
