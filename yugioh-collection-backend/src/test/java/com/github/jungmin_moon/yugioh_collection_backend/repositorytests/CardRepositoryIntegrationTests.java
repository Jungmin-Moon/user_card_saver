package com.github.jungmin_moon.yugioh_collection_backend.repositorytests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import com.github.jungmin_moon.yugioh_collection_backend.entities.Card;
import com.github.jungmin_moon.yugioh_collection_backend.repositories.CardRepository;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CardRepositoryIntegrationTests {
	
	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	TestEntityManager entityManager;
	
	//Setup of needed objects
	
	Card card1 = new Card();
	Card card2 = new Card();
	Card card3 = new Card();
	
	@BeforeEach
	void setup() {
		
		card1.setCardName("Dark Magician");
		card1.setCardType("Normal Monster");
		card1.setQuantity(4);
		card1.setUsername("TestUser3");
		
		card2.setCardName("Blue-Eyes White Dragon");
		card2.setCardType("Normal Monster");
		card2.setQuantity(6);
		card2.setUsername("TestUser3");
		
		card3.setCardName("Red-Eyes Black Dragon");
		card3.setCardType("Normal Monster");
		card3.setQuantity(4);
		card3.setUsername("TestUser3");
		
	}
	
	//Success tests
	
	@Test
	@DisplayName("Test method to make sure inserted card is the same as card1")
	void givenNewCard_WhenSave_ThenSuccess() {
		
		Card insertedCard = cardRepository.save(card1);
		
		assertThat(entityManager.find(Card.class, insertedCard.getId())).isEqualTo(card1);
	} 
	
	@Test
	@DisplayName("Test method to check that card found by name is equal to given card.")
	void givenCardCreated_WhenFindByName_ThenSuccess() {
		
		cardRepository.save(card1);
		
		Card retrievedCard = cardRepository.getCardInfo("TestUser3", "Dark Magician");
		
		assertThat(retrievedCard).hasFieldOrPropertyWithValue("cardName", retrievedCard.getCardName());
		
	}
	
	@Test
	@DisplayName("Test method to check that number of cards owned by user is 2")
	void givenCardsCreated_WhenCardsCountSame_ThenSuccess() {
		
		cardRepository.save(card1);
		cardRepository.save(card2);
		
		int cardCollectionSize = cardRepository.getAllOwnedByUsername("TestUser3").size();
		
		assertThat(cardCollectionSize == 2);
		
	}
	
	@Test
	@DisplayName("Test method that checks size of list when searching with a substring is equal to 1")
	void givenCardsCreated_WhenGivenSubStringFound_ThenListIsSizeOne() {
		
		cardRepository.save(card1);
		cardRepository.save(card2);
		
		var cardsWithSubStringInName = cardRepository.getCardsWithWordInName("TestUser3", "Mag");
		
		assertThat(cardsWithSubStringInName.size() == 1);
	}
	
	@Test
	@DisplayName("Test method to check when searching for cards with specific quantities.")
	void givenCardsCreated_WhenGivenQuantity_ThenListIsSizeTwo() {
		
		cardRepository.save(card1);
		cardRepository.save(card2);
		cardRepository.save(card3);
		
		var cardsWithGivenQuantity = cardRepository.getCardsByQuantity("TestUser3", 4);
		
		assertThat(cardsWithGivenQuantity.size() == 2);
	}
	
	
}
